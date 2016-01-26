package org.almkg.verticles;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.NetSocketStream;
import io.vertx.core.parsetools.RecordParser;
import io.vertx.core.parsetools.impl.RecordParserImpl;
import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.impl.PumpFactoryImpl;
import io.vertx.ext.reactivestreams.ReactiveReadStream;
import io.vertx.rx.java.RxHelper;
import rx.Observable;

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
                logger.info("Сервер запущен!");
            } else {
                logger.info("Сервер не удалось запустить!");
            }
        });
    }
}
