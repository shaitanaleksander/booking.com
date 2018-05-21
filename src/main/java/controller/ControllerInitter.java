package controller;

import com.google.gson.Gson;

public class ControllerInitter {

    public static void initAllControllers(){

        Gson gson = new Gson();
        UserController.initControllers(gson);
    }
}
