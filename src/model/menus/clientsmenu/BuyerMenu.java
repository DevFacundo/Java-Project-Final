package model.menus.clientsmenu;

import model.clients.Buyer;
import model.exceptions.InvalidInputException;
import model.genericManagement.JsonClass;
import model.menus.clientsmenu.clientMenuService.BuyerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class BuyerMenu {
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        Integer option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        addBuyer();
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
        System.out.println("│         BUYER MENU            │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD BUYER                  │");
        System.out.println("│ 2. MODIFY A BUYER             │");
        System.out.println("│ 3. REMOVE A BUYER             │");
        System.out.println("│ 4. VIEW ALL BUYERS            │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

    private void addBuyer() {
        Boolean continueAdding = true;
        do {
            try {
                List<Buyer> buyers = new ArrayList<>();
                buyers = JsonClass.loadList("buyers.json", Buyer.class);
                Buyer newBuyer = BuyerService.createBuyer(scanner);
                System.out.println("Buyer added successfully:");
                System.out.println(newBuyer);
                if (!buyers.isEmpty()) {
                    Buyer b = buyers.get(buyers.size() - 1);
                    Integer lastId = b.getId() + 1;
                    newBuyer.setId(lastId);
                }
                buyers.add(newBuyer);
                JsonClass.saveList(buyers, "buyers.json");
            } catch (InvalidInputException e) {
                System.out.println("Error adding buyer: " + e.getMessage());
            }

            continueAdding = askToContinue();
        } while (continueAdding);
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another buyer? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}
