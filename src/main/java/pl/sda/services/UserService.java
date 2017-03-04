package pl.sda.services;

import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.entities.User;
import pl.sda.servlets.responses.CreateUserResponse;
import pl.sda.servlets.responses.DeleteUserResponse;
import pl.sda.servlets.responses.UpdateUserResponse;
import pl.sda.storage.Storage;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by m.losK on 2017-03-04.
 */
public class UserService {
    public CreateUserResponse addUser(String userJson) {
        ObjectMapper mapper = new ObjectMapper();
        CreateUserResponse response = new CreateUserResponse();

        try {
            User user = mapper.readValue(userJson, User.class);
            UUID id = UUID.randomUUID();
            user.setId(id);

            Storage.addUser(user);
            response.setStatus("OK");
            response.setId(id.toString());
        } catch (IOException e) {
            response.setError(e.getMessage());
        }

        return response;
    }

    public User getUserByUUID(String id) {
        User result = null;
        if (id != null && !id.isEmpty()) {
            UUID uuid = UUID.fromString(id);
            for (User user : Storage.getUsers()) {
                if (uuid.equals(user.getId())) {
                    result = user;
                    break;
                }
            }
        }
        return result;
    }

    public DeleteUserResponse removeUserById(String id) {
        DeleteUserResponse response = new DeleteUserResponse();
        response.setMessage("USER WITH ID: " + id + " NOT FOUND");
        if (id != null && !id.isEmpty()) {
            User tempUser = null;
            UUID uuid = UUID.fromString(id);
            for (User user : Storage.getUsers()) {
                if (uuid.equals(user.getId())) {
                    tempUser = user;
                    break;
                }
            }
            Storage.removeUser(tempUser);
            response.setStatus("OK");
            response.setMessage("USER WITH ID: " + id + " HAS BEEN REMOVED");
        }
        return response;
    }

    public UpdateUserResponse updateUser(User user) {
        UpdateUserResponse response = new UpdateUserResponse();

        Storage.updateUser(user);
        response.setStatus("OK");
        response.setMessage("USER UPDATED");

        return response;
    }
}