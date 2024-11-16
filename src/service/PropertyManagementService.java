package service;

import model.exceptions.InvalidInputException;
import model.login.Login;

import java.util.Scanner;

public class PropertyManagementService {

    Login login = new Login();
    Scanner scanner = new Scanner(System.in);

//    /// Simula limpiar la consola
//    private void clearConsole() {
//        for (int i = 0; i < 50; i++) {
//            System.out.println();
//        }
//    }

    /// Metodo del menu
    public void menu() {
        if (login.authenticate()) { /// Si el login esta correcto...
            int option = -1;
            do {
//                clearConsole();
                printMenu(); /// Imprime un menu modularizado

                try {
                    option = getValidatedOption(); /// Excepcion que valida que sea un numero
//                    clearConsole();

                    switch (option) {
                        case 1:
                            System.out.println("Opcion 1");
                            break;
                        case 2:
                            System.out.println("Opcion 2");
                            break;
                        case 3:
                            System.out.println("Opcion 3");
                            break;
                        case 4:
                            System.out.println("Opcion 4");
                            break;
                        case 0:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    if (option != 0) {
                        System.out.println("\nPress Enter to return to the menu...");
                        scanner.nextLine();
                    }
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Press Enter to try again...");
                    scanner.nextLine();
                }
            } while (option != 0);
        } else {
            System.out.println("Exiting...");
        }
    }


    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│               MENU            │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. RENTS MANAGEMENT           │");
        System.out.println("│ 2. SALES MANAGEMENT           │");
        System.out.println("│ 3. PROPERTIES MANAGEMENT      │");
        System.out.println("│ 4. CLIENTS MANAGEMENT         │");
        System.out.println("│ 0. EXIT                       │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

    private int getValidatedOption() throws InvalidInputException {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input must be a number. Please try again.");
        }
    }
}
