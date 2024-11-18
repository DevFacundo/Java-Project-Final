package ui.menus.rentsmenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import ui.menus.rentsmenu.rentMenuService.RentService;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class RentsMenu {

    Scanner scanner;
    RentService rs;

    public RentsMenu() {
        scanner = new Scanner(System.in);
        rs = new RentService();
    }


    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        rs.addRent();
                        break;
                    case 2:
                        rs.modifyRent();
                        break;
                    case 3:
                        rs.deleteRent();
                        break;
                    case 4:
                        rs.seeAllRents();
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
                System.out.println(e.getMessage());
            }
        } while (option != 0);
    }


    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│         RENTS MENU            │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD RENT                   │");
        System.out.println("│ 2. MODIFY A RENT              │");
        System.out.println("│ 3. CANCEL A RENT              │");
        System.out.println("│ 4. VIEW ALL RENTS             │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
