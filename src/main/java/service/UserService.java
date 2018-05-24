package service;

import repositiry.UserRepository;

public class UserService {

    private UserRepository userRepository = UserRepository.getRepository();
    private static UserService userService;

    private UserService() {
    }

    public boolean logInUser(String name, String password) {
        if ((name.equals("") || name == null) && (password.equals("") || password == null)) {
            return false;
        }
        var user = userRepository.getUserByNameAndPasword(name, password);
        return user != null;
    }

    public static UserService getUserService() {
        if (userService == null) userService = new UserService();
        return userService;
    }

}
