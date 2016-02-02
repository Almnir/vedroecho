package org.almkg.database;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yarnykh on 02.02.2016.
 */
public class DBConnection implements IDBConnection {

    Logger logger = LoggerFactory.getLogger(DBConnection.class);

    SQLConnection connection;

    @Override
    public SQLConnection getConnection(Vertx vertx) {
        JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()
                .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
                .put("driver_class", "org.hsqldb.jdbcDriver"));
        client.getConnection(res -> {
            if (res.succeeded()) {
                connection = res.result();
            } else {
                logger.fatal("Cannot create DB connection!");
            }
        });
        return connection;
    }
}