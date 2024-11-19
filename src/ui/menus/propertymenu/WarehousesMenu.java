package ui.menus.propertymenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import service.propertyService.WarehousesServices;

import java.util.Scanner;

import static utils.Utils.getValidatedOption;

public class WarehousesMenu {
    WarehousesServices wservice;
    Scanner scanner ;

    public WarehousesMenu() {
        wservice = new WarehousesServices();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        wservice.addWarehouse();
                        break;
                    case 2:
                        wservice.modifyWarehouse();
                        break;
                    case 3:
                        wservice.deleteProperty();
                        break;
                    case 4:
                        wservice.seeAllWareHouses();
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
            } catch (ElementNotFoundException e) {
                System.out.println("Error: "+e.getMessage());
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│       WAREHOUSES MENU         │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD WAREHOUSE              │");
        System.out.println("│ 2. MODIFY WAREHOUSE           │");
        System.out.println("│ 3. CANCEL WAREHOUSE           │");
        System.out.println("│ 4. VIEW WAREHOUSES            │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
