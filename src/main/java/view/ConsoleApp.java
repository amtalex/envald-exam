package view;

import java.util.Scanner;

public class UsersAndDevicesApp {

    public static void main(String[] args) {

    }

    void console() {

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

        Scanner scanHeight = new Scanner(System.in);
        int commNum = scanHeight.nextInt();
        switch (commNum) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;

            default:
                System.out.println("Enter correct command number!");
        }
    }
}
