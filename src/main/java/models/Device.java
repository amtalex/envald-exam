package models;

import java.util.Date;

public class Device {

    private Type type;
    private String name;
    private String model;
    private Date date;
    private Boolean issued;

    enum Type {
        LAPTOP,
        TABLET,
        PHONE
    }

    public Device(Type type, String name, String model, Date date) {
        this.type = type;
        this.name = name;
        this.model = model;
        this.date = date;
    }
}
