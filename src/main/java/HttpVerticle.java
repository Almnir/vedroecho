import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Created by yarnykh on 14.01.2016.
 */
public class HttpVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(HttpVerticle.class);

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/").handler(ctx -> {
            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
            ctx.response().end("Привет, мир!");
        });

        router.route().handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(router::accept).listen(8080, "localhost", res -> {
            if (res.succeeded()) {
                logger.info("HTTP сервир слушаит!");
//                logger.info("HTTP сервир слушаит!");
            } else {
                logger.info("HTTP cервир не встал!");
            }
        });
    }
}
