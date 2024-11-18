package model.menus.clientsmenu;

import model.clients.Tenant;
import model.exceptions.InvalidInputException;
import model.genericManagement.JsonUtils;
import model.menus.clientsmenu.clientMenuService.TenantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class TenantMenu {
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        Integer option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        addTenant();
                        break;
                    case 2:
                        System.out.println("Opcion 2");
                        break;
                    case 3:
                        System.out.println("Opcion 3");
                        break;
                    case 4:
                        System.out.println("Opcion 4");
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

    private void addTenant() {
        Boolean continueAdding = true;
        do {
            try {
                List<Tenant> tenants = new ArrayList<>();
                tenants = JsonUtils.loadList("tenants.json", Tenant.class);
                Tenant newTenant = TenantService.createTenant(scanner);
                System.out.println("Tenant added successfully:");
                System.out.println(newTenant);
                if (!tenants.isEmpty()) {
                    Tenant t = tenants.get(tenants.size() - 1);
                    Integer lastId = t.getId() + 1;
                    newTenant.setId(lastId);
                }
                tenants.add(newTenant);
                JsonUtils.saveList(tenants, "tenants.json", Tenant.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding tenant: " + e.getMessage());
            }

            continueAdding = askToContinue();
        } while (continueAdding);
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another tenant? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}
