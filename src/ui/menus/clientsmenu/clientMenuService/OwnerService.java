package ui.menus.clientsmenu.clientMenuService;

import model.clients.Owner;
import model.exceptions.InvalidInputException;
import model.genericManagement.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.utils.Utils.validateInputs;

public class OwnerService {
    Scanner scanner = new Scanner(System.in);

    public static Owner createOwner(Scanner scanner) throws InvalidInputException {
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

        return new Owner(name, surname, dni, contactNumber, email, address);
    }

    public void addOwner() {
        Boolean continueAdding = true;
        do {
            try {
                List<Owner> owners = new ArrayList<>();
                owners = JsonUtils.loadList("owners.json", Owner.class);
                Owner newOwner = OwnerService.createOwner(scanner);
                System.out.println("Owner added successfully:");
                System.out.println(newOwner);
                if (!owners.isEmpty()) {
                    Owner o = owners.getLast();
                    Integer lastId = o.getId() + 1;
                    newOwner.setId(lastId);
                }
                owners.add(newOwner);
                JsonUtils.saveList(owners,"owners.json", Owner.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding owner: " + e.getMessage());
            }

            continueAdding = askToContinue();
        } while (continueAdding);
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another owner? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

}
