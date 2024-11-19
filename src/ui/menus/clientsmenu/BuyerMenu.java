package ui.menus.clientsmenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import ui.menus.clientsmenu.clientMenuService.BuyerService;
import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class BuyerMenu {
    Scanner scanner;
    BuyerService buyerService;

    public BuyerMenu() {
        scanner = new Scanner(System.in);
        buyerService = new BuyerService();
    }

    public void menu() {
        Integer option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        buyerService.addBuyer();
                        break;
                    case 2:
                        buyerService.modifyBuyer();
                        break;
                    case 3:
                        buyerService.deleteBuyer();
                        break;
                    case 4:
                        buyerService.seeAllBuyers();
                        break;
                    case 0:
                        System.out.println("Returning to the previous menu...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidInputException | ElementNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│         BUYER MENU            │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD BUYER                  │");
        System.out.println("│ 2. MODIFY A BUYER             │");
        System.out.println("│ 3. REMOVE A BUYER             │");
        System.out.println("│ 4. VIEW ALL BUYERS            │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
