package org.coursemed.classes;

import java.util.List;

// Singleton class to track and manage logged user
public class LoggingManager {

    private static User loggedUser = null;

    private LoggingManager() {

    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static User login(List<? extends User> userList, String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    loggedUser = user;

                    return loggedUser;
                }
            }
        }

        return null;
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static void logout() {
        loggedUser = null;
    }
}
