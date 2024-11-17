package service;

import model.exceptions.InvalidInputException;
import model.login.Login;
import model.menus.clientsmenu.ClientsMenu;
import model.menus.propertymenu.PropertyMenu;
import model.menus.rentsmenu.RentsMenu;
import model.menus.salesmenu.SalesMenu;

import java.util.Scanner;

public class PropertyManagementService {
    RentsMenu rentsMenu = new RentsMenu();
    SalesMenu salesMenu = new SalesMenu();
    PropertyMenu propertyMenu = new PropertyMenu();
    ClientsMenu clientsMenu = new ClientsMenu();

    Login login = new Login();
    Scanner scanner = new Scanner(System.in);

    /// Simula limpiar la consola
    private void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

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
                            rentsMenu.menu();
                            break;
                        case 2:
                            System.out.println("Opcion 2");
                            salesMenu.menu();
                            break;
                        case 3:
                            System.out.println("Opcion 3");
                            propertyMenu.menu();

                            break;
                        case 4:
                            System.out.println("Opcion 4");
                            clientsMenu.menu();
                            break;
                        case 0:
                            System.out.println("Leaving the program....");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
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