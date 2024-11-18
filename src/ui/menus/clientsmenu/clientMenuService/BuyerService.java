package ui.menus.clientsmenu.clientMenuService;

import model.State;
import model.clients.Buyer;
import model.exceptions.*;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.utils.Utils;

import java.util.Scanner;

import static model.utils.Utils.*;

public class BuyerService {

    Scanner scanner;
    GenericClass <Buyer> buyers;

    public BuyerService() {
        scanner = new Scanner(System.in);
        buyers = new GenericClass<>(JsonUtils.loadList("buyers.json",Buyer.class));
    }

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
                buyers = new GenericClass<>(JsonUtils.loadList("buyers.json",Buyer.class));
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
            } catch (DuplicateElementException e) {
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

    public void modifyBuyer() {
        try {

            buyers = new GenericClass<>(JsonUtils.loadList("buyers.json",Buyer.class));
            if (buyers.isEmpty()) {
                System.out.println("No buyers available to modify.");
                return;
            }

            System.out.print("Enter the DNI of the buyer you want to modify: ");
            String dni = scanner.nextLine().trim();

            Buyer buyerToModify = null;
            for (Buyer buyer : buyers.returnList()) {
                if (buyer.getDni().equals(dni)) {
                    buyerToModify = buyer;
                    break;
                }
            }

            if (buyerToModify == null) {
                throw new InvalidInputException("Buyer with DNI " + dni + " not found.");
            }

            if (buyerToModify.getClientState()== State.BOUGHT)
            {
                throw new SoldException("You can't modify "+buyerToModify.getName()+" "+
                        buyerToModify.getSurname()+" because they have already bought a propierty");
            }


            System.out.println("Selected Buyer: " + buyerToModify);

            modifyBuyerAttributes(buyerToModify);

            JsonUtils.saveList(buyers.returnList(), "buyers.json", Buyer.class);
            System.out.println("Buyer modified successfully!");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SoldException e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }

    private void modifyBuyerAttributes(Buyer buyer) throws InvalidInputException {
        boolean continueModifying = true;
        while (continueModifying) {
            System.out.println("\n----------------------------------------------------");
            System.out.println("     Modify Buyer Attributes");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Name");
            System.out.println("2. Surname");
            System.out.println("3. Contact Number");
            System.out.println("4. Email");
            System.out.println("5. Address");
            System.out.println("0. Go back");
            System.out.println("----------------------------------------------------");
            System.out.println("Please select the attribute you would like to modify:");

            Integer option;
            try {
                option = getValidatedOption();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine().trim();
                    validateName(name);
                    buyer.setName(name);
                    break;

                case 2:
                    System.out.print("Enter new surname: ");
                    String surname = scanner.nextLine().trim();
                    validateSurname(surname);
                    buyer.setSurname(surname);
                    break;

                case 3:
                    System.out.print("Enter new contact number: ");
                    String contactNumber = scanner.nextLine().trim();
                    validateContactNumber(contactNumber);
                    buyer.setContactNumber(contactNumber);
                    break;

                case 4:
                    System.out.print("Enter new email: ");
                    String email = scanner.nextLine().trim();
                    validateEmail(email);
                    buyer.setEmail(email);
                    break;

                case 5:
                    System.out.print("Enter new address: ");
                    String address = scanner.nextLine().trim();
                    validateAddress(address);
                    buyer.setAdress(address);
                    break;

                case 0:
                    continueModifying = false;
                    System.out.println("Modification process finished.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    public void deleteBuyer() {
        try {

            buyers = new GenericClass<>(JsonUtils.loadList("buyers.json",Buyer.class));
            if (buyers.isEmpty()) {
                System.out.println("No buyers available to delete.");
                return;
            }

            System.out.print("Enter the DNI of the buyer you want to delete: ");
            String dni = scanner.nextLine().trim();

            Buyer buyerToDelete = null;
            for (Buyer buyer : buyers.returnList()) {
                if (buyer.getDni().equals(dni)) {
                    buyerToDelete = buyer;
                    break;
                }
            }

            if (buyerToDelete == null) {
                throw new InvalidInputException("Buyer with DNI " + dni + " not found.");
            }

            if (buyerToDelete.getClientState()== State.BOUGHT)
            {
                throw new SoldException("You can't delete "+buyerToDelete.getName()+" "+
                        buyerToDelete.getSurname()+" because they have already bought a propierty");
            }

            System.out.println("Selected Buyer: " + buyerToDelete);

            buyers.deleteElement(buyerToDelete);

            JsonUtils.saveList(buyers.returnList(), "buyers.json", Buyer.class);
            System.out.println("Buyer deleted successfully!");
        } catch (InvalidInputException | SoldException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void seeAllBuyers() throws ElementNotFoundException {

        if (buyers.isEmpty()) {
            throw new ElementNotFoundException("No buyers found.");
        }
        System.out.println(buyers.returnList());
    }

}
