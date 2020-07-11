package models;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {

    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private String role;
    private String team;
    private List<Device> deviceList;

    public User(String lastName, String firstName, String email, String role, String team) {
        this.id = UUID.randomUUID().toString();
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
        this.team = team;
    }

    public User(String id, String lastName, String firstName, String email, String role, String team) {
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return lastName.equals(user.lastName) &&
                firstName.equals(user.firstName) &&
                email.equals(user.email) &&
                role.equals(user.role) &&
                team.equals(user.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, role, team);
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", team='" + team + '\'' +
                ", deviceList=" + deviceList +
                '}';
    }
}
