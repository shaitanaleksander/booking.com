package controller;

import com.google.gson.Gson;
import model.User;
import service.JWTService;
import service.UserService;

import static spark.Spark.*;

class UserController {

    static void initControllers(Gson gson) {

        var jwnService = JWTService.getJwtService();
        var userService = UserService.getUserService();

        get("/api/user", (req, res) -> {
            var userId = jwnService.verifyToken(req.headers("token"));
            return userService.getUserById(userId);
        }, gson::toJson);

        get("/api/users/:id", ((req, res) -> {
            var userId = req.params("id");
            return userService.getUserById(userId);
        }), gson::toJson);

        post("/api/users", (req, res) -> {
            var user = gson.fromJson(req.body(), User.class);
            return userService.saveUser(user);
        }, gson::toJson);

        put("/api/users", (req, res) -> {
            var user = gson.fromJson(req.body(), User.class);
            userService.updateUser(user);
            return user;
        }, gson::toJson);

        delete("/api/users/:id", (req, res) -> {
            var userId = req.params("id");
            userService.deleteUser(userId);
            return "";
        });
    }
}
