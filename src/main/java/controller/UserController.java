package controller;

import com.google.gson.Gson;
import model.User;
import repositiry.UserRepository;
import service.JWTService;

import static spark.Spark.*;

class UserController {

    static void initControllers(Gson gson) {

        var userRepository = UserRepository.getRepository();
        var jwnService = JWTService.getJwtService();
        get("/api/users/:id", (req, res) -> {

            var token = req.headers("token");

            if (!jwnService.verifyToken(token)) {
                res.status(401);
                return "unauthorized";
            }

            var userId = req.params("id");
            res.status(200);
            return userRepository.getUser(userId);
        }, gson::toJson);

        post("/api/users", (req, res) -> {
            var user = gson.fromJson(req.body(), User.class);

            user = userRepository.saveUser(user);
            res.status(201);
            return user;
        }, gson::toJson);

        put("/api/users", (req, res) -> {
            var user = gson.fromJson(req.body(), User.class);
            userRepository.updateUser(user);
            res.status(200);
            return "";
        }, gson::toJson);

        delete("/api/users/:id", (req, res) -> {
            var userId = req.params("id");
            userRepository.deleteUser(userId);
            res.status(202);
            return "";
        });
    }
}
