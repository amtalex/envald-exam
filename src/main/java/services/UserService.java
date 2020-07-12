package services.users;

import exception.EntityNotFoundException;
import models.Device;
import models.User;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class UserService implements UserAPI {

    private List<User> userList;

    private User getUserRequired(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(User.class, id);
        });
    }

    @Override
    public List<User> getUsers() {
        return userList;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(User user) {
    }

    @Override
    public User findBy(String param, String value) {
        for (User u : userList){
            if ()
        }
        return null;
    }

    @Override
    public List<Device> showDevices(User user) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users WHERE id=?;")) {
                prepStmt.setString(1, user.getId());

                ResultSet rs = prepStmt.executeQuery();
                //List<Device> userList = new ArrayList<>();
                if (rs.next()) {
                    return userList.addAll(
                            rs.getObject("deviceList"), Object userList);
                } else {
                    throw new RuntimeException();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void printReport(User user) {

    }
}
