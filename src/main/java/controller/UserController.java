package controller;

import com.google.gson.Gson;
import model.User;
import repositiry.UserRepository;

import static spark.Spark.*;

class UserController {

    static void initControllers(Gson gson) {
        var userRepository = new UserRepository();

        get("/user/:name", (req, res) -> {
            var userName =  req.params(":name");
            var user =  new User().setName(userName);
            res.status(200);
            return userRepository.getUser(user);

        }, gson::toJson);

        post("/user", (req, res) -> {
            var user = gson.fromJson(req.body(), User.class);
            userRepository.saveUser(user);
            res.status(200);
            return ""; // TODO: 19.05.18 return user from repository
        });
    }
}
