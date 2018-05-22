package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import model.serializer.UserSerializer;

public class ControllerInitter {

    public static void initAllControllers(){

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserSerializer());
        Gson gson = builder.setPrettyPrinting().create();

        UserController.initControllers(gson);
    }
}
