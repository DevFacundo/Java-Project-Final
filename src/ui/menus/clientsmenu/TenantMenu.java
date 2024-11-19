package ui.menus.clientsmenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import service.clientService.TenantService;
import java.util.Scanner;

import static utils.Utils.getValidatedOption;

public class TenantMenu {
    TenantService tenantService;
    Scanner scanner;

    public TenantMenu() {
        tenantService = new TenantService();
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
                        tenantService.addTenant();
                        break;
                    case 2:
                        tenantService.modifyTenant();
                        break;
                    case 3:
                        tenantService.deleteTenant();
                        break;
                    case 4:
                       tenantService.seeAllTenants();
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
        System.out.println("│        TENANT MENU            │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD TENANT                 │");
        System.out.println("│ 2. MODIFY A TENANT            │");
        System.out.println("│ 3. REMOVE A TENANT            │");
        System.out.println("│ 4. VIEW ALL TENANTS           │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
