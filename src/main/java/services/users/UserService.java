package services.users;

import models.Device;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserAPI {

    public static final String dbURL = "jdbc:sqlite:users.db";
    private List<Device> list;

    @Override
    public List<User> getUsers() {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users;")) {

                List<User> users = new ArrayList<>();

                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    users.add((new User(
                            rs.getString("id"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("team")
                    )));
                }
                prepStmt.execute();
                return users;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {

        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users WHERE ?=?;")) {
                prepStmt.setString(1, param);
                prepStmt.setString(2, value);

                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    return new User(
                            rs.getString("id"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("team"));
                } else {
                    throw new RuntimeException();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(User user) {

        try (Connection connection = DriverManager.getConnection(dbURL);
             PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
            prepStmt.setObject(1, id);
            prepStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findBy(String param, String value) {

        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users WHERE ?=?;")) {
                prepStmt.setString(1, param);
                prepStmt.setString(2, value);

                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    return new User(
                            rs.getString("id"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("team"));
                } else {
                    throw new RuntimeException();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Device> showDevices(User user) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users WHERE id=?;")) {
                prepStmt.setString(1, user.getId());

                ResultSet rs = prepStmt.executeQuery();
                //List<Device> list = new ArrayList<>();
                if (rs.next()) {
                    return list.addAll(
                            rs.getObject("deviceList"), Object list);
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
}
