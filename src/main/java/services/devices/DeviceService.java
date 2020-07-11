package services.devices;

import models.Device;
import models.User;

import java.util.List;

/**
 * 1) добавление нового оборудования "на склад"
 * 2) удаление оборудования (нельзя удалить, если оборудование выдано пользователю)
 * 3) выдача оборудования пользователю
 * 4) поиск оборудования по типу / по "выдано/на_складе" / по пользователю
 * 5) список всего оборудования
 */
public interface DeviceService {

    void addDevice(Device device);

    void deleteDevice(Device device);

    void giveDevice (Device device, User user);

    Device findBy(String param, String value);

    List<Device> getDevices();

}
