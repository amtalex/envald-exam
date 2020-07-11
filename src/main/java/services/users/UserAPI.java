package services.users;

import models.Device;
import models.User;

import java.util.List;

/**
 * 1) просмотр списка уже существующих пользователей
 * 2) добавление нового пользователя
 * 3) удаление пользователя
 * 4) поиск пользователя по id / email / фамилии
 * 5) показать список оборудования у пользователя
 * 6) создание отчета по пользователям - текстовый файл в виде:
 * """
 * [uesrId] - Lastname Firstname - email:
 * 1) Laptop - Apple Macbook Pro 15 - 14.05.2019
 * 2) Phone - Apple iPhone X - 14.05.2019
 */
public interface UserAPI {

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(User user);

    User findBy(String param, String value);

    List<Device> showDevices(User user);

}
