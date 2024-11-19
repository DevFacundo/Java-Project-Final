package ui.menus.mainMenu;

import model.exceptions.InvalidInputException;
import ui.menus.login.Login;
import ui.menus.clientsmenu.TypeOfClientMenu;
import ui.menus.propertymenu.PropertyMenu;
import ui.menus.rentsmenu.RentsMenu;
import ui.menus.reportsMenu.ReportMenu;
import ui.menus.salesmenu.SalesMenu;

import java.util.Scanner;

import static utils.Utils.getValidatedOption;
public class MainMenu {
    RentsMenu rentsMenu = new RentsMenu();
    SalesMenu salesMenu = new SalesMenu();
    PropertyMenu propertyMenu = new PropertyMenu();
    TypeOfClientMenu typeOfClientMenu = new TypeOfClientMenu();
    ReportMenu reportMenu = new ReportMenu();

    Login login = new Login();
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        if (login.authenticate()) {
            int option = -1;
            do {
                printMenu();

                try {
                    option = getValidatedOption();

                    switch (option) {
                        case 1:
                            System.out.println("Option 1");
                            rentsMenu.menu();
                            break;
                        case 2:
                            System.out.println("Option 2");
                            salesMenu.menu();
                            break;
                        case 3:
                            System.out.println("Option 3");
                            propertyMenu.menu();

                            break;
                        case 4:
                            System.out.println("Option 4");
                            typeOfClientMenu.menu();
                            break;
                        case 5:
                            System.out.println("Option 5");
                            reportMenu.menu();
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
        System.out.println("│ 5. REPORTS                    │");
        System.out.println("│ 0. EXIT                       │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }
}
