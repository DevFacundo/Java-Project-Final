package model.menus.propertymenu;

import model.exceptions.InvalidInputException;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class PropertyMenu {

    Scanner scanner = new Scanner(System.in);

    /// Método del menú
    public void menu() {
        int option = -1;
        do {
            printMenu(); /// Imprime un menú modularizado

            try {
                option = getValidatedOption(); /// Excepción que valida que sea un número

                switch (option) {
                    case 1:
                        System.out.println("You selected: Houses");
                        HousesMenu housesMenu = new HousesMenu();
                        housesMenu.menu();
                        break;
                    case 2:
                        System.out.println("You selected: Apartments");
                        ApartmentsMenu apartmentsMenu = new ApartmentsMenu();
                        apartmentsMenu.menu();
                        break;
                    case 3:
                        System.out.println("You selected: Stores");
                        StoresMenu storesMenu = new StoresMenu();
                        storesMenu.menu();
                        break;
                    case 4:
                        System.out.println("You selected: Lots");
                        LotsMenu lotsMenu = new LotsMenu();
                        lotsMenu.menu();
                        break;
                    case 5:
                        System.out.println("You selected: Warehouses");
                        WarehousesMenu warehousesMenu = new WarehousesMenu();
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

  /*  private int getValidatedOption() throws InvalidInputException {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input must be a number. Please try again.");
        }
    }*/
}
