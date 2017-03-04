package pl.sda.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.entities.User;
import pl.sda.services.UserService;
import pl.sda.servlets.responses.CreateUserResponse;
import pl.sda.servlets.responses.DeleteUserResponse;
import pl.sda.servlets.responses.UpdateUserResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by m.losK on 2017-03-04.
 */
public class JSONReceiver extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();

        StringBuffer json = new StringBuffer();

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            json.append(line);
        }

        UserService userService = new UserService();
        CreateUserResponse response = userService.addUser(json.toString());

        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(response));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        UserService userService = new UserService();
        User userByUUID = userService.getUserByUUID(userId);

        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(userByUUID));
        resp.setContentType("application/json");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        UserService userService = new UserService();
        User userByUUID = userService.getUserByUUID(userId);
        DeleteUserResponse result = userService.removeUserById(userId);

        ObjectMapper mapper = new ObjectMapper();

        resp.getWriter().write(mapper.writeValueAsString(result));
        resp.setContentType("application/json");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        StringBuffer json = new StringBuffer();

        String line = null;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json.toString(), User.class);

        UserService userService = new UserService();
        UpdateUserResponse response = userService.updateUser(user);

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}
