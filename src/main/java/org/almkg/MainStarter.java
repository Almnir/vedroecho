package org.almkg;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.jgroups.JGroupsClusterManager;
import org.almkg.verticles.HttpVerticle;
import org.almkg.verticles.TCPVerticle;
import org.almkg.verticles.WebsocketVerticle;

import java.util.function.Consumer;

/**
 * Created by alm on 30.01.2016.
 * Класс запуска вертексов
 */
public class MainStarter {

    private static Logger logger = LoggerFactory.getLogger(MainStarter.class);

    public static void main(String[] args) {
        // инстанцируем вертексы
        logger.info("Инстанцирование вертексов...");
        long millisecondsStart = System.currentTimeMillis();
        TCPVerticle tcpVerticle = new TCPVerticle();
        HttpVerticle httpVerticle = new HttpVerticle();
        WebsocketVerticle websocketVerticle = new WebsocketVerticle();
        // создаём менеджер кластеров
        ClusterManager mgr = new JGroupsClusterManager();
        // вертексы будут кластеризованы
        VertxOptions options = new VertxOptions().setClustered(true);
        options.setClusterManager(mgr);
        DeploymentOptions deploymentOptions = new DeploymentOptions();
        // стартуем вертексы
        runVerticle(tcpVerticle, options, deploymentOptions);
        runVerticle(httpVerticle, options, deploymentOptions);
        runVerticle(websocketVerticle, options, deploymentOptions);
        logger.info("Вертексы инстанцированы успешно!");
        long timeSpentInMilliseconds = System.currentTimeMillis() - millisecondsStart;
        logger.info("Время запуска: " + timeSpentInMilliseconds + " ms");
    }

    /**
     * Запуск вертекса с опциями
     *
     * @param verticleID идентификатор вертекса
     * @param options опции вертекса
     * @param deployOption опции деплоя
     */
    static void runVerticle(AbstractVerticle verticleID, VertxOptions options, DeploymentOptions deployOption) {
        Consumer<Vertx> runner = vertx -> {
            try {
                vertx.deployVerticle(verticleID, deployOption);
            } catch (Throwable t) {
                logger.fatal("Verticle failed to deploy, VerticleID = " + verticleID);
                t.printStackTrace();
            }
        };
        if (options.isClustered()) {
            Vertx.clusteredVertx(options, res -> {
                if (res.succeeded()) {
                    Vertx vertx = res.result();
                    runner.accept(vertx);
                } else {
                    res.cause().printStackTrace();
                }
            });
        } else {
            Vertx vertx = Vertx.vertx(options);
            runner.accept(vertx);
        }
    }

}
