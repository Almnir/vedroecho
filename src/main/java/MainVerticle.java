import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.AsyncResultHandler;
import io.vertx.core.logging.Logger;

import static io.vertx.core.logging.LoggerFactory.getLogger;

/**
 * Created by yarnykh on 15.01.2016.
 */
public class MainVerticle extends AbstractVerticle {

    Logger logger = getLogger(MainVerticle.class);

    @Override
    public void start() throws Exception {

        // деплоим TCP сервер
        vertx.deployVerticle("TCPVerticle", new AsyncResultHandler<String>() {
                    @Override
                    public void handle(AsyncResult<String> stringAsyncResult) {
                        defaultHandler(stringAsyncResult);
                    }
        });

        // деплоим HTTP сервер
        vertx.deployVerticle("HttpVerticle", new AsyncResultHandler<String>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                defaultHandler(stringAsyncResult);
            }
        });
        // деплоим Websocket сервер
        vertx.deployVerticle("WebsocketVerticle", new AsyncResultHandler<String>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                defaultHandler(stringAsyncResult);
            }
        });

    }

    void defaultHandler(AsyncResult<String> res) {
        if (res.succeeded()) {
            String deploymentID = res.result();
            logger.info("Verticle deployed ok, deploymentID = " + deploymentID);
        } else {
            logger.fatal("Verticle failed to deploy, deploymentID = " + res.result());
            res.cause().printStackTrace();
        }
    }

}
