package controllers;

import models.Device;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceDataVaseController {

    private static final String dbURL = "jdbc:sqlite:users.db";

    public void addDevice(Device device) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement(
                    "INSERT INTO devices (id,type,deviceName,model,issuedDate,issued,userId) " +
                            "VALUES (?,?,?,?,?,?);")) {
                prepStmt.setString(1, device.getId());
                prepStmt.setObject(2, device.getType());
                prepStmt.setString(3, device.getDeviceName());
                prepStmt.setString(4, device.getModel());
                prepStmt.setTimestamp(5, device.getIssuedDate());
                prepStmt.setBoolean(6, device.getIssued());
                prepStmt.setString(7, device.getUserId());
                prepStmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> selectAll() {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (Statement prepStmt = connection.createStatement()) {

                List<User> devices = new ArrayList<>();
                ResultSet rs = prepStmt.executeQuery("SELECT * FROM devices;");
                if (rs.next()) {
                    devices.add((new Device(
                            rs.getString("id"),
                            rs.getObject("type", Device.class),
                            rs.getString("deviceName"),
                            rs.getString("model"),
                            rs.getString("issuedDate"),
                            rs.getString("issued"),
                            rs.getString("userId")
                    )));
                }
                return devices;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findBy(String param, String value) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users WHERE ?=?;")) {
                prepStmt.setString(1, param);
                prepStmt.setString(2, value);

                ResultSet rs = prepStmt.getResultSet();
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getString("id"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("team")));
                } else {
                    return Optional.empty();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement(
                    "UPDATE users SET " +
                            "lastName = ?, " +
                            "firstName = ?, " +
                            "email = ?, " +
                            "role = ?, " +
                            "team = ? WHERE id = ?;")) {
                prepStmt.setString(1, user.getLastName());
                prepStmt.setString(2, user.getFirstName());
                prepStmt.setString(3, user.getEmail());
                prepStmt.setString(4, user.getRole());
                prepStmt.setString(5, user.getTeam());
                prepStmt.setObject(6, user.getId());
                prepStmt.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
                prepStmt.setString(1, user.getId());
                prepStmt.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
