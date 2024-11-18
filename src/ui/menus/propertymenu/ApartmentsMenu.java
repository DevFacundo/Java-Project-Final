package ui.menus.propertymenu;

import model.exceptions.InvalidInputException;
import ui.menus.propertymenu.propertyMenuService.ApartmentsService;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class ApartmentsMenu {
    ApartmentsService apartmentsService = new ApartmentsService();
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        apartmentsService.addApartament();
                        break;
                    case 2:
                        System.out.println("Modifying an apartment...");
                        break;
                    case 3:
                        System.out.println("Cancelling an apartment...");
                        break;
                    case 4:
                        System.out.println("Viewing all apartments...");
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
        System.out.println("│       APARTMENTS MENU         │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD APARTMENT              │");
        System.out.println("│ 2. MODIFY APARTMENT           │");
        System.out.println("│ 3. CANCEL APARTMENT           │");
        System.out.println("│ 4. VIEW APARTMENTS            │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
