package controller;

import com.google.gson.Gson;
import model.User;
import repositiry.UserRepository;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

class UserController {

    static void initControllers(Gson gson) {

        var userRepository = new UserRepository();

        get("/api/users/:id", (req, res) -> {
            var userId = req.params("id");
            res.status(200);
            return userRepository.getUser(userId);
        }, gson::toJson);

        post("/api/users", (req, res) -> {
            var user = gson.fromJson(req.body(), User.class);

            user = userRepository.saveUser(user);
            res.status(200);
            return user;
        }, gson::toJson);

        put("/api/users", (req, res) -> {
            var user = gson.fromJson(req.body(), User.class);
            userRepository.updateUser(user);
            res.status(200);
            return "";
        }, gson::toJson);
    }
}
