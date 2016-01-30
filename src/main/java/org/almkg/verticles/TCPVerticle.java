package org.almkg.verticles;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

import java.util.ArrayList;

import static io.vertx.core.logging.LoggerFactory.getLogger;

/**
 * Created by yarnykh on 14.01.2016.
 */
public class TCPVerticle extends AbstractVerticle {

    Logger logger = getLogger(TCPVerticle.class);

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);

    }

    @Override
    public void start() throws Exception {
        NetServer server = vertx.createNetServer();
        ArrayList<String> myList = new ArrayList<String>();
        server.connectHandler(socket -> {
            myList.add(socket.writeHandlerID());
            socket.handler(RecordParser.newDelimited("\n", buffer -> {
                logger.info("Получил данные!"+ buffer.toString());
                for ( String s: myList) {
                    if (!s.equals(socket.writeHandlerID())) {
                        vertx.eventBus().publish(s, buffer);
                    }
                }
                //socket.write(buffer);
            }));
            socket.closeHandler(v -> {
                myList.remove(socket.writeHandlerID());
                logger.info("Обработчик закрылся");
            });
        });

        server.listen(4321, "localhost", res -> {
            if (res.succeeded()) {
                logger.info("TCP Сервер запущен!");
            } else {
                logger.info("TCP Сервер не удалось запустить!");
            }
        });
    }
}
