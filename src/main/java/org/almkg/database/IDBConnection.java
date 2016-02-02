package org.almkg.database;

import io.vertx.core.Vertx;
import io.vertx.ext.sql.SQLConnection;

/**
 * Created by yarnykh on 02.02.2016.
 */
public interface IDBConnection {
    public SQLConnection getConnection(Vertx vertx);
}
