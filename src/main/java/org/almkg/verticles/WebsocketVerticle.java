package org.almkg.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by yarnykh on 14.01.2016.
 */
public class WebsocketVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(AbstractVerticle.class);

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.websocketHandler(websocket -> {
            logger.info("Вебсокет канект!");
            websocket.handler(buffer -> {
                logger.info(buffer);
            });
        });
        server.listen(8081, "localhost", res -> {
            if (res.succeeded()) {
                logger.info("HTTP Websocket сервер слушаит!");
            } else {
                logger.info("HTTP Websocket cервер не встал!");
            }
        });
    }
}
