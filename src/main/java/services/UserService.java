package services;

import controllers.DeviceDataBaseController;
import controllers.UserDataBaseController;
import exception.EntityNotFoundException;
import models.Device;
import models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 1) просмотр списка уже существующих пользователей
 * 2) добавление нового пользователя
 * 3) удаление пользователя
 * 4) поиск пользователя по id / email / фамилии
 * 5) показать список оборудования у пользователя
 * 6) создание отчета по пользователям - текстовый файл в виде:
 * """
 * [userId] - Lastname Firstname - email:
 * 1) Laptop - Apple Macbook Pro 15 - 14.05.2019
 * 2) Phone - Apple iPhone X - 14.05.2019
 */
public class UserService {

    private UserDataBaseController controller = UserDataBaseController.getInstance();

    private User getUserRequired(String id) {
        User user = null;
        List<User> userList = controller.selectAll();
        for (User u : userList) {
            if (u.getId().equals(id)) {
                user = u;
            } else throw new EntityNotFoundException(User.class, id);
        }
        return user;
    }

    public List<User> getUsers() {
        return controller.selectAll();
    }

    public void addUser(User user) {
        controller.add(user);
    }

    public void deleteUser(User user) {
        controller.delete(user);
    }

    public User findBy(String param, String value) {
        Optional<User> user = controller.findBy(param, value);
        return user.get();
    }

    public List<Device> showDevices(User user) {
        Optional<List<Device>> userDevices = DeviceDataBaseController.getInstance()
                .findBy("userId", user.getId());
        return userDevices.get();
    }

    public void printReport(User user) {
        try {
            File file = new File(user.getLastName() + "_report.txt");
            FileWriter fWriter = new FileWriter(file);


            StringBuilder device = new StringBuilder();
            int i = 0;
            for (Device d : showDevices(user)) {
                device.append(i + 1).append(" ").append(d.getType())
                        .append(" - ").append(d.getDeviceName()).append(d.getModel())
                        .append(" - ").append(d.getIssuedDate()).append('\n');
            }
            fWriter.append("[").append(user.getId()).append(" - ")
                    .append(user.getLastName()).append(user.getFirstName())
                    .append(" - ").append(user.getEmail()).append('\n').append(device);

            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
