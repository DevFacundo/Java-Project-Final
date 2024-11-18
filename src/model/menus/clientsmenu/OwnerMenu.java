package model.menus.clientsmenu;

import model.clients.Owner;
import model.exceptions.InvalidInputException;
import model.genericManagement.JsonUtils;
import model.menus.clientsmenu.clientMenuService.OwnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class OwnerMenu {
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        Integer option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        addOwner();
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

    private void addOwner() {
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
