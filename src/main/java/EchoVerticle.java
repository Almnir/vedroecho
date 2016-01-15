import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetServer;

/**
 * Created by yarnykh on 14.01.2016.
 */
public class EchoVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(EchoVerticle.class);

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
