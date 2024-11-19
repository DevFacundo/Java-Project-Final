package ui.menus.propertymenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import ui.menus.propertymenu.propertyMenuService.StoresService;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class StoresMenu {
    StoresService storesService;
    Scanner scanner;

    public StoresMenu() {
        storesService = new StoresService();
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
                        storesService.addStore();
                        break;
                    case 2:
                        storesService.modifyStore();
                        break;
                    case 3:
                        storesService.deleteProperty();
                        break;
                    case 4:
                        storesService.seeAllStores();
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
        System.out.println("│          STORES MENU          │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD STORE                  │");
        System.out.println("│ 2. MODIFY STORE               │");
        System.out.println("│ 3. CANCEL STORE               │");
        System.out.println("│ 4. VIEW STORES                │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
