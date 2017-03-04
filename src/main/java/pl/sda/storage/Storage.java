package pl.sda.storage;

import pl.sda.entities.User;
import pl.sda.servlets.responses.UpdateUserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by m.losK on 2017-03-04.
 */
public class Storage {

    private static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void updateUser(User user) {
        UUID id = user.getId();
        for (User userToUpdate : users) {
            if (userToUpdate.getId().equals(id)) {
                userToUpdate.setLogin(user.getLogin());
                userToUpdate.setMail(user.getMail());
                userToUpdate.setName(user.getName());
            }
        }
    }
}
