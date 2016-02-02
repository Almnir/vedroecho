package org.almkg.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.jdbc.JDBCAuth;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.almkg.database.DBConnection;
import org.almkg.database.QueryDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yarnykh on 14.01.2016.
 */
public class HttpVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(HttpVerticle.class);

    @Override
    public void start() throws Exception {

        DBConnection db = new DBConnection();
        QueryDAO dao = new QueryDAO(vertx, db);

        setUpInitialData("jdbc:hsqldb:mem:test?shutdown=true");

        // Create a JDBC client with a test database
        JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()
                .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
                .put("driver_class", "org.hsqldb.jdbcDriver"));

        Router router = Router.router(vertx);

        // We need cookies, sessions and request bodies
        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        // Simple auth service which uses a JDBC data source
        AuthProvider authProvider = JDBCAuth.create(client);

        // We need a user session handler too to make sure the user is stored in the session between requests
        router.route().handler(UserSessionHandler.create(authProvider));

        // Any requests to URI starting '/private/' require login
        router.route("/private/*").handler(RedirectAuthHandler.create(authProvider, "/loginpage.html"));

        // Serve the static private pages from directory 'private'
        router.route("/private/*").handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("private"));

        // Handles the actual login
        router.route("/loginhandler").handler(FormLoginHandler.create(authProvider));

        // Implement logout
        router.route("/logout").handler(context -> {
            context.clearUser();
            // Redirect back to the index page
            context.response().putHeader("location", "/").setStatusCode(302).end();
        });

        // Serve the non private static pages
        router.route().handler(StaticHandler.create().setWebRoot("public"));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    private Connection conn;

    private void setUpInitialData(String url) throws SQLException {
        conn = DriverManager.getConnection(url);
        executeStatement("drop table if exists user;");
        executeStatement("drop table if exists user_roles;");
        executeStatement("drop table if exists roles_perms;");
        executeStatement("create table user (username varchar(255), password varchar(255), password_salt varchar(255) );");
        executeStatement("create table user_roles (username varchar(255), role varchar(255));");
        executeStatement("create table roles_perms (role varchar(255), perm varchar(255));");

        // sha512, sha256 как соль
        executeStatement("insert into user values ('alm', '4343750033735f7bd3005d63fa83980de8e21b587c92b3d06681cead4ce5733a0e23377e2fd854b0732fcb022a98c7a3324be3a907c232f2ed3ce0aa26be7472', '5d546560c6bf78fc7df8aa986c11c71cf7f83bcf3058bd9b1e8b1c56d0f5b852');");
        executeStatement("insert into user_roles values ('alm', 'dev');");
        executeStatement("insert into user_roles values ('alm', 'admin');");
        executeStatement("insert into roles_perms values ('dev', 'commit_code');");
        executeStatement("insert into roles_perms values ('dev', 'eat_pizza');");
        executeStatement("insert into roles_perms values ('admin', 'merge_pr');");
    }

    private void executeStatement(String sql) throws SQLException {
        conn.createStatement().execute(sql);
    }
}
