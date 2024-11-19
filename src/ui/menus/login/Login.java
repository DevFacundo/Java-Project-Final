package ui.menus.login;

import java.util.Scanner;

public class Login {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final Integer MAX_ATTEMPTS = 3;


    public boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        Integer attempts = 0;


        while (attempts < MAX_ATTEMPTS) {
            System.out.println("Enter username: ");
            String enteredUsername = scanner.nextLine();

            System.out.println("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (enteredUsername.equals(USERNAME) && enteredPassword.equals(PASSWORD)) {
                return true;
            } else {
                attempts++;
                System.out.println("Invalid username or password. Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }
        System.out.println("You have exceeded the maximum number of attempts.");
        return false;
    }

}
