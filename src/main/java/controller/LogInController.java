package controller;

import service.JWTService;
import service.UserService;

import static spark.Spark.*;

public class LogInController {


    public static void initControllers() {
        UserService userService = UserService.getUserService();
        var jwnService = JWTService.getJwtService();
        get("/api/logIn/:name/:password", (req, res) -> {
            var name = req.params("name");
            var password = req.params("password");
            var user = userService.logInUser(name, password);
            if (user == null) {
                res.status(404);
                return "";
            }
            res.status(200);
            return jwnService.createToken(user.getId().toHexString());
        });
    }
}



