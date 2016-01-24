import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetServer;

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
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                logger.info("Получил данные!"+ buffer.toString());
                socket.write(buffer);
            });
            socket.closeHandler(v -> {
                logger.info("Сокет закрылься...ваааай!");
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
