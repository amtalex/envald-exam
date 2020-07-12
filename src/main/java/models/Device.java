package models;

import java.util.Objects;
import java.util.UUID;

public class Device {

    private String id;
    private String type;
    private String deviceName;
    private String model;
    private String issuedDate;
    private Boolean issued;
    private String userId;

    enum Type {
        LAPTOP,
        TABLET,
        PHONE
    }

    public Device(String type, String deviceName, String model) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.deviceName = deviceName;
        this.model = model;
        this.issuedDate = null;
        this.issued = false;

    }

    public Device(String id, String type, String deviceName, String model,
                  String issuedDate, Boolean issued, String userId) {
        this.id = id;
        this.type = type;
        this.deviceName = deviceName;
        this.model = model;
        this.issuedDate = issuedDate;
        this.issued = issued;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getModel() {
        return model;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public Boolean getIssued() {
        return issued;
    }

    public String getUserId() {
        return userId;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setIssued(Boolean issued) {
        this.issued = issued;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return getType() == device.getType() &&
                getDeviceName().equals(device.getDeviceName()) &&
                getModel().equals(device.getModel()) &&
                getIssuedDate().equals(device.getIssuedDate()) &&
                getIssued().equals(device.getIssued()) &&
                getUserId().equals(device.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getDeviceName(), getModel(), getIssuedDate(), getIssued(), getUserId());
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", model='" + model + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                ", issued=" + issued +
                ", userId='" + userId + '\'' +
                '}';
    }
}
