package ui.menus.clientsmenu.clientMenuService;

import model.clients.Tenant;
import model.exceptions.InvalidInputException;
import model.genericManagement.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.utils.Utils.validateInputs;

public class TenantService {
    Scanner scanner = new Scanner(System.in);

    public static Tenant createTenant(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine().trim();

        System.out.print("Enter DNI: ");
        String dni = scanner.nextLine().trim();

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();

        validateInputs(name, surname, contactNumber, email, address, dni);

        return new Tenant(name, surname, dni, contactNumber, email, address);
    }

    public void addTenant() {
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
