package model.menus.clientsmenu;

import model.exceptions.InvalidInputException;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class TypeOfClientMenu {
    Scanner scanner = new Scanner(System.in);
    BuyerMenu buyerMenu = new BuyerMenu();
    TenantMenu tenantMenu = new TenantMenu();
    OwnerMenu ownerMenu = new OwnerMenu();

    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        ownerMenu.menu();
                        break;
                    case 2:
                        buyerMenu.menu();
                        break;
                    case 3:
                        tenantMenu.menu();
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
        System.out.println("│         CLIENTS MENU          │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. OWNER                      │");
        System.out.println("│ 2. BUYER                      │");
        System.out.println("│ 3. TENANT                     │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("│                               │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }
}
