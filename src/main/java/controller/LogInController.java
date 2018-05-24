package controller;

import service.UserService;

import static spark.Spark.*;

public class LogInController {


    public static void initControllers() {
        UserService userService = UserService.getUserService();

        get("/api/logIn/:name/:password", (req, res) -> {
            var name = req.params("name");
            var password = req.params("password");
            var token = userService.logInUser(name, password);
            res.status(200);
            System.out.println(token);
            return token;
        });
    }
}



