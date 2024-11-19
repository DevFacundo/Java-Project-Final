package ui.menus.clientsmenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import ui.menus.clientsmenu.clientMenuService.OwnerService;
import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class OwnerMenu {
    OwnerService ownerService ;
    Scanner scanner;

    public OwnerMenu() {
        ownerService = new OwnerService();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        Integer option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        ownerService.addOwner();
                        break;
                    case 2:
                        ownerService.modifyOwner();
                        break;
                    case 3:
                        ownerService.deleteOwner();
                        break;
                    case 4:
                        ownerService.seeAllOwners();
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
        System.out.println("│         OWNER MENU            │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD OWNER                  │");
        System.out.println("│ 2. MODIFY AN OWNER            │");
        System.out.println("│ 3. REMOVE AN OWNER            │");
        System.out.println("│ 4. VIEW ALL OWNERS            │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
