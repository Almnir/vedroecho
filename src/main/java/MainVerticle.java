import io.vertx.core.AbstractVerticle;

/**
 * Created by yarnykh on 15.01.2016.
 */
public class MainVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        vertx.deployVerticle("EchoVerticle");
        vertx.deployVerticle("HttpVerticle");
        vertx.deployVerticle("HttpWebsocketVerticle");

    }
}
