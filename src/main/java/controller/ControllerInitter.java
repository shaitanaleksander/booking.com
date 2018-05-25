package controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import customException.UserDosntExistException;
import model.User;
import model.serializer.UserSerializer;
import service.JWTService;

import static spark.Spark.*;

public class ControllerInitter {

    public static void initAllControllers() {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserSerializer());
        Gson gson = builder.setPrettyPrinting().create();
        var jwnService = JWTService.getJwtService();

        UserController.initControllers(gson);
        LogInController.initControllers();

        before("^/api/logIn", (req, res) -> {
            var token = req.headers("token");
            try {
                jwnService.verifyToken(token);
            } catch (JWTVerificationException e) {
                halt(401, "Go away!");
            }
        });

        after((request, response) -> {
            var method = request.requestMethod();
            switch (method) {
                case "GET":
                    response.status(200);
                    break;
                case "POST":
                    response.status(201);
                    break;
                case "DELETE":
                    response.status(202);
                    break;
                case "PUT":
                    response.status(200);
                    break;
            }
        });

        exception(UserDosntExistException.class, (exception, request, response) -> {
            response.status(404);
            response.body(exception.getMessage());
        });
    }
}
