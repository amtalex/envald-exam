package services;

import controllers.DeviceDataBaseController;
import models.Device;
import models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * 1) добавление нового оборудования "на склад"
 * 2) удаление оборудования (нельзя удалить, если оборудование выдано пользователю)
 * 3) выдача оборудования пользователю
 * 4) поиск оборудования по типу / по "выдано/на_складе" / по пользователю
 * 5) список всего оборудования
 */
public class DeviceService {

    private DeviceDataBaseController controller = DeviceDataBaseController.getInstance();

    private DeviceService() {
    }

    List<Device> getDevices() {
        return controller.selectAll();
    }

    void addDevice(Device device) {
        controller.add(device);
    }

    void deleteDevice(Device device) {
        if (!device.getIssued())
            controller.delete(device);
    }

    List<Device> findBy(String param, String value) {
        Optional<List<Device>> devices = controller.findBy(param, value);
        return devices.get();
    }

    void giveDevice(Device device, User user) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        device.setIssued(true);
        device.setIssuedDate(date.format(formatter));
        device.setUserId(user.getId());

        controller.update(device);
    }

}
