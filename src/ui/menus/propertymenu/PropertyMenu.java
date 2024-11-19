package ui.menus.propertymenu;

import model.exceptions.InvalidInputException;

import java.util.Scanner;

import static utils.Utils.getValidatedOption;

public class PropertyMenu {
    HousesMenu housesMenu = new HousesMenu();
    ApartmentsMenu apartmentsMenu = new ApartmentsMenu();
    StoresMenu storesMenu = new StoresMenu();
    LotsMenu lotsMenu = new LotsMenu();
    WarehousesMenu warehousesMenu = new WarehousesMenu();

    Scanner scanner = new Scanner(System.in);


    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        System.out.println("You selected: Houses");
                        housesMenu.menu();
                        break;
                    case 2:
                        System.out.println("You selected: Apartments");

                        apartmentsMenu.menu();
                        break;
                    case 3:
                        System.out.println("You selected: Stores");

                        storesMenu.menu();
                        break;
                    case 4:
                        System.out.println("You selected: Lots");

                        lotsMenu.menu();
                        break;
                    case 5:
                        System.out.println("You selected: Warehouses");

                        warehousesMenu.menu();
                        break;
                    case 0:
                        System.out.println("Returning to the previous menu...");
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
    }

    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│         PROPERTY MENU         │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. HOUSES                     │");
        System.out.println("│ 2. APARTMENTS                 │");
        System.out.println("│ 3. STORES                     │");
        System.out.println("│ 4. LOTS                       │");
        System.out.println("│ 5. WAREHOUSES                 │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
