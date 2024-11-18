package ui.menus.propertymenu;

import model.exceptions.InvalidInputException;
import ui.menus.propertymenu.propertyMenuService.LotsService;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class LotsMenu {
    LotsService lotsService;
    Scanner scanner;

    public LotsMenu() {
       lotsService = new LotsService();
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
                        lotsService.addLot();
                        break;
                    case 2:
                        lotsService.modifyLot();
                        break;
                    case 3:
                        System.out.println("Cancelling a lot...");
                        break;
                    case 4:
                        System.out.println("Viewing all lots...");
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
        System.out.println("│           LOTS MENU           │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD LOT                    │");
        System.out.println("│ 2. MODIFY LOT                 │");
        System.out.println("│ 3. CANCEL LOT                 │");
        System.out.println("│ 4. VIEW LOTS                  │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
