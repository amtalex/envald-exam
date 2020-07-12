package view;

import models.Device;
import models.User;
import services.DeviceService;
import services.UserService;

import java.util.List;
import java.util.Scanner;

class ConsoleCommands {

    private static final UserService userService = new UserService();
    private static final DeviceService deviceService = new DeviceService();

    static void showUserList() {
        for (User u : userService.getUsers()) {
            System.out.println(u);
        }
    }

    static void addNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input new user values according to parameters:" +
                "\nLastName/FirstName/Email/Role/Team");
        userService.addUser(new User(
                scanner.nextLine(),
                scanner.nextLine(),
                scanner.nextLine(),
                scanner.nextLine(),
                scanner.nextLine()
        ));
    }

    static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input user's LastName to delete:");
        userService.findBy(
                "lastName",
                scanner.nextLine()
        );
    }

    static void searchUserByParam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input user's parameter and value to find:");
        userService.findBy(
                scanner.nextLine(),
                scanner.nextLine()
        );
    }

    static void showUserDevices() {
        for (Device d : deviceService.getDevices()) {
            System.out.println(d);
        }
    }

    static void printReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input user's LastName to delete:");
        userService.findBy(
                "lastName",
                scanner.nextLine()
        );
    }

    static void addNewDevice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input new user values according to parameters:" +
                "\nType[LAPTOP/TABLET/PHONE]/Device Name/Model");
        deviceService.addDevice(new Device(
                scanner.nextLine(),
                scanner.nextLine(),
                scanner.nextLine()
        ));
    }

    static void deleteDevice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input device's Name or Model and it's value to delete:");
        List<Device> devicesToDelete = deviceService.findBy(
                scanner.nextLine(),
                scanner.nextLine()
        );
        deviceService.deleteDevice(devicesToDelete.get(scanner.nextInt()));
    }

    static void givingDeviceToUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input device's Name or Model and it's value to give out the device:");
        List<Device> freeDevices = deviceService.findBy(
                scanner.nextLine(),
                scanner.nextLine()
        );
        System.out.println("Input user's LastName to give out the device him:");
        User user = userService.findBy(
                "lastName",
                scanner.nextLine()
        );
        System.out.println("Available devices to give out:\n" + freeDevices + "Enter number of needed device");
        deviceService.giveDevice(
                freeDevices.get(scanner.nextInt()),
                user);
    }

    static void searchDeviceByParam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input device's parameter and value to find:");
        deviceService.findBy(
                scanner.nextLine(),
                scanner.nextLine()
        );
    }

    static void showDevicesList() {
        for (Device d : deviceService.getDevices()) {
            System.out.println(d);
        }
    }


}
