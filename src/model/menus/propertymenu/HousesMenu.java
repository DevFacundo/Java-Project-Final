package model.menus.propertymenu;

import model.exceptions.InvalidInputException;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class HousesMenu {
    Scanner scanner = new Scanner(System.in);


    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        System.out.println("Adding a house...");
                        break;
                    case 2:
                        System.out.println("Modifying a house...");
                        break;
                    case 3:
                        System.out.println("Cancelling a house...");
                        break;
                    case 4:
                        System.out.println("Viewing all houses...");
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
        System.out.println("│          HOUSES MENU          │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD HOUSE                  │");
        System.out.println("│ 2. MODIFY HOUSE               │");
        System.out.println("│ 3. CANCEL HOUSE               │");
        System.out.println("│ 4. VIEW HOUSES                │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }
}