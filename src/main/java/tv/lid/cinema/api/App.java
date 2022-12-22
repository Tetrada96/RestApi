package tv.lid.cinema.api;

import spark.Spark;
import static spark.Spark.*;

import tv.lid.cinema.api.controllers.ScheduleController;
// import tv.lid.cinema.api.models.ScheduleModel;

import static tv.lid.cinema.api.transformers.JsonTransformer.JSON;

// класс приложения
public final class App {
    public  static final String JSON_MIME_TYPE = "application/json";

    private static final String SERVER_HOST    = "127.0.0.1";
    private static final int    SERVER_PORT    = 8808;

    public static void main(final String[] args) {

        // создание контроллера
        final ScheduleController controller = new ScheduleController();

        // конфигурация сервера
        ipAddress(SERVER_HOST);
        port(SERVER_PORT);


        path("/api", ()-> {
            get("/test", controller.test, JSON);
        });
        after((req, res) -> {
            res.type(JSON_MIME_TYPE);
        });

        // хук завершения работы
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // останов сервера
                Spark.stop();
            }
        });
    }
}
