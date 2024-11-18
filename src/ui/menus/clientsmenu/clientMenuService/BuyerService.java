package ui.menus.clientsmenu.clientMenuService;

import model.clients.Buyer;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;

import java.util.Scanner;

import static model.utils.Utils.validateInputs;

public class BuyerService {

    Scanner scanner = new Scanner(System.in);

    public static Buyer createBuyer(Scanner scanner) throws InvalidInputException {
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

        return new Buyer(name, surname, dni, contactNumber, email, address);
    }

    public void addBuyer() {
        Boolean continueAdding = true;
        do {
            try {
                GenericClass<Buyer> buyers = new GenericClass<>(JsonUtils.loadList("buyers.json", Buyer.class));

                Buyer newBuyer = BuyerService.createBuyer(scanner);

                System.out.println("Buyer added successfully:");
                System.out.println(newBuyer);
                if (!buyers.isEmpty()) {
                    Buyer b = buyers.getLastObject();
                    Integer lastId = b.getId() + 1;
                    newBuyer.setId(lastId);
                }
                buyers.addElement(newBuyer);
                JsonUtils.saveList(buyers.returnList(), "buyers.json", Buyer.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding buyer: " + e.getMessage());
            } catch (DuplicateElementException e)
            {
                System.out.println("Error: " + e.getMessage());
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
