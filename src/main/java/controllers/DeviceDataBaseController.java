package controllers;

import configurators.DataBaseConfig;
import exception.EntityNotFoundException;
import models.Device;
import repositories.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceDataBaseController implements CrudRepository<Device> {

    private static final String tableName = "device.db";
    private static final String dbURL = (DataBaseConfig.getInstance().getDbUrl()).concat(tableName);

    private DeviceDataBaseController() {
    }

    public static DeviceDataBaseController getInstance() {
        return new DeviceDataBaseController();
    }

    public void add(Device device) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement(
                    "INSERT INTO devices (id,type,deviceName,model,issuedDate,issued,userId) " +
                            "VALUES (?,?,?,?,?,?);")) {
                prepStmt.setString(1, device.getId());
                prepStmt.setString(2, device.getType());
                prepStmt.setString(3, device.getDeviceName());
                prepStmt.setString(4, device.getModel());
                prepStmt.setString(5, device.getIssuedDate());
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

    public List<Device> selectAll() {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (Statement prepStmt = connection.createStatement()) {

                List<Device> devices = new ArrayList<>();
                ResultSet rs = prepStmt.executeQuery("SELECT * FROM devices;");
                if (rs.next()) {
                    devices.add((new Device(
                            rs.getString("id"),
                            rs.getString("type"),
                            rs.getString("deviceName"),
                            rs.getString("model"),
                            rs.getString("issuedDate"),
                            rs.getBoolean("issued"),
                            rs.getString("userId"))));
                }
                return devices;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Device>> findBy(String param, String value) {
        Optional<Device> optionalDevice;
        List<Device> deviceList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users WHERE ?=?;")) {
                prepStmt.setString(1, param);
                prepStmt.setString(2, value);

                ResultSet rs = prepStmt.getResultSet();
                if (rs.next()) {
                    optionalDevice = Optional.of(new Device(
                            rs.getString("id"),
                            rs.getString("type"),
                            rs.getString("deviceName"),
                            rs.getString("model"),
                            rs.getString("issuedDate"),
                            rs.getBoolean("issued"),
                            rs.getString("userId")));
                    optionalDevice.ifPresent(deviceList::add);

                } else {
                    throw new EntityNotFoundException(Device.class, param + " / " + value);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(deviceList).isPresent() ? Optional.of(deviceList) : Optional.empty();
    }

    public void update(Device device) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement(
                    "UPDATE devices SET " +
                            "type = ?, " +
                            "deviceName = ?, " +
                            "model = ?, " +
                            "issuedDate = ?, " +
                            "issued = ?, " +
                            "userId = ? WHERE id = ?;")) {
                prepStmt.setString(1, device.getType());
                prepStmt.setString(2, device.getDeviceName());
                prepStmt.setString(3, device.getModel());
                prepStmt.setString(4, device.getIssuedDate());
                prepStmt.setBoolean(5, device.getIssued());
                prepStmt.setString(6, device.getUserId());
                prepStmt.setString(7, device.getId());
                prepStmt.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Device device) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM devices WHERE id=?;")) {
                prepStmt.setString(1, device.getId());
                prepStmt.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
