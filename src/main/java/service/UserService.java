package service;

import customException.UserDosntExistException;
import model.User;
import repositiry.UserRepository;
import spark.utils.StringUtils;

public class UserService {

    private UserRepository userRepository = UserRepository.getRepository();
    private static UserService userService;

    private UserService() {
    }

    public User logInUser(String name, String password) {
        if (StringUtils.isBlank(name) && StringUtils.isBlank(password)) {
            return null;
        }
        return userRepository.getUserByNameAndPassword(name, password);
    }

    public User getUserById(String id) throws UserDosntExistException {
        var user = userRepository.getUser(id);
        if (user == null) {
            throw new UserDosntExistException();
        }
        return user;
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteUser(userId);
    }

    public static UserService getUserService() {
        if (userService == null) userService = new UserService();
        return userService;
    }
}
