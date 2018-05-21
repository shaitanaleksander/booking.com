import controller.ControllerInitter;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        port(8080);
        ControllerInitter.initAllControllers();
    }
}
