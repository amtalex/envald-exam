package view;

import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {

        ConsoleApp.run();
    }

    static void run() {

        System.out.println("Available commands..." +
                "\nUsers commands:" +
                "\n1 - Show users list" +
                "\n2 - Add new user" +
                "\n3 - Delete user" +
                "\n4 - Search user by parameter" +
                "\n5 - Show user's list of devices" +
                "\n6 - Report output by the user" +
                "\n-------------------------------" +
                "\nDevices commands" +
                "\n7 - Add new device to warehouse" +
                "\n8 - Delete device" +
                "\n9 - Device distribution to user" +
                "\n10 - Search devices by parameter" +
                "\n11 - Show devices list" +
                "\nTo select a command, enter it's number");

        Scanner scanComm = new Scanner(System.in);
        int commNum = scanComm.nextInt();
        switch (commNum) {
            case 1:
                ConsoleCommands.showUserList();
                break;
            case 2:
                ConsoleCommands.addNewUser();
                break;
            case 3:
                ConsoleCommands.deleteUser();
                break;
            case 4:
                ConsoleCommands.searchUserByParam();
                break;
            case 5:
                ConsoleCommands.showUserDevices();
                break;
            case 6:
                ConsoleCommands.printReport();
                break;
            case 7:
                ConsoleCommands.addNewDevice();
                break;
            case 8:
                ConsoleCommands.deleteDevice();
                break;
            case 9:
                ConsoleCommands.givingDeviceToUser();
                break;
            case 10:
                ConsoleCommands.searchDeviceByParam();
                break;
            case 11:
                ConsoleCommands.showDevicesList();
                break;

            default:
                System.out.println("Enter correct command number!");
        }
    }
}
