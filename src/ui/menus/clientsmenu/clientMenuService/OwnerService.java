package ui.menus.clientsmenu.clientMenuService;

import model.State;
import model.clients.Owner;
import model.exceptions.*;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.utils.Utils;
import java.util.Scanner;

import static model.utils.Utils.*;

public class OwnerService {

    Scanner scanner;
    GenericClass<Owner> owners;

    public OwnerService() {
        scanner = new Scanner(System.in);
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
    }

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
        Boolean continueAdding;
        do {
            try {

                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
                Owner newOwner = OwnerService.createOwner(scanner);
                System.out.println("Owner added successfully:");
                System.out.println(newOwner);
                if (!owners.isEmpty()) {
                    Owner o = owners.getLastObject();
                    Integer lastId = o.getId() + 1;
                    newOwner.setId(lastId);
                }
                owners.addElement(newOwner);
                JsonUtils.saveList(owners.returnList(), "owners.json", Owner.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding owner: " + e.getMessage());
            } catch (DuplicateElementException e) {
                System.out.println("The Owner has already exists");
            }

            continueAdding = askToContinue();
        } while (continueAdding);
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another owner? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    public void modifyOwner() {
        try {

            owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
            if (owners.isEmpty()) {
                System.out.println("No owners available to modify.");
                return;
            }

            System.out.print("Enter the DNI of the owner you want to modify: ");
            String dni = scanner.nextLine().trim();

            Owner ownerToModify = null;
            for (Owner owner : owners.returnList()) {
                if (owner.getDni().equals(dni)) {
                    ownerToModify = owner;
                    break;
                }
            }

            if (ownerToModify == null) {
                throw new InvalidInputException("Owner with DNI " + dni + " not found.");
            }
            if (ownerToModify.getClientState() == State.RENTED) {
                throw new RentedException("You can't modify " + ownerToModify.getName() + " " +
                        ownerToModify.getSurname() + " because they have already rented a propierty");
            }

            if (ownerToModify.getClientState() == State.SOLD) {
                throw new SoldException("You can't modify " + ownerToModify.getName() + " " +
                        ownerToModify.getSurname() + " because they have already sold a propierty");
            }

            System.out.println("Selected Owner: " + ownerToModify);

            modifyOwnerAttributes(ownerToModify);

            JsonUtils.saveList(owners.returnList(), "owners.json", Owner.class);
            System.out.println("Owner modified successfully!");
        } catch (InvalidInputException | RentedException | SoldException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void modifyOwnerAttributes(Owner owner) throws InvalidInputException {
        boolean continueModifying = true;
        while (continueModifying) {
            System.out.println("\n----------------------------------------------------");
            System.out.println("     Modify Owner Attributes");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Name");
            System.out.println("2. Surname");
            System.out.println("3. Contact Number");
            System.out.println("4. Email");
            System.out.println("5. Address");
            System.out.println("0. Go back");
            System.out.println("----------------------------------------------------");
            System.out.println("Please select the attribute you would like to modify:");

            Integer choice;
            try {
                choice = Utils.getValidatedOption();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine().trim();
                    validateName(name);
                    owner.setName(name);
                    break;

                case 2:
                    System.out.print("Enter new surname: ");
                    String surname = scanner.nextLine().trim();
                    validateSurname(surname);
                    owner.setSurname(surname);
                    break;

                case 3:
                    System.out.print("Enter new contact number: ");
                    String contactNumber = scanner.nextLine().trim();
                    validateContactNumber(contactNumber);
                    owner.setContactNumber(contactNumber);
                    break;

                case 4:
                    System.out.print("Enter new email: ");
                    String email = scanner.nextLine().trim();
                    validateEmail(email);
                    owner.setEmail(email);
                    break;

                case 5:
                    System.out.print("Enter new address: ");
                    String address = scanner.nextLine().trim();
                    validateAddress(address);
                    owner.setAdress(address);
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

    public void seeAllOwners() throws ElementNotFoundException {
        if (owners.isEmpty()) {
            throw new ElementNotFoundException("No owners found.");
        }
        System.out.println(owners.returnList());
    }

    public void deleteOwner() {
        try {
            owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
            if (owners.isEmpty()) {
                System.out.println("No Owners available to delete.");
                return;
            }

            System.out.print("Enter the DNI of the owner you want to delete: ");
            String dni = scanner.nextLine().trim();

            Owner ownerToDelete = null;
            for (Owner o : owners.returnList()) {
                if (o.getDni().equals(dni)) {
                    ownerToDelete = o;
                    break;
                }
            }

            if (ownerToDelete == null) {
                throw new InvalidInputException("Owner with DNI " + dni + " not found.");
            }
            if (ownerToDelete.getClientState() == State.RENTED) {
                throw new RentedException("You can't delete " + ownerToDelete.getName() + " " +
                        ownerToDelete.getSurname() + " because they have already rented a propierty");
            }

            if (ownerToDelete.getClientState() == State.SOLD) {
                throw new SoldException("You can't delete " + ownerToDelete.getName() + " " +
                        ownerToDelete.getSurname() + " because they have already sold a propierty");
            }

            System.out.println("Selected Owner: " + ownerToDelete);

            owners.deleteElement(ownerToDelete);

            JsonUtils.saveList(owners.returnList(), "owners.json", Owner.class);
            System.out.println("Owner deleted successfully!");
        } catch (InvalidInputException | SoldException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RentedException e) {
            System.out.println("Error:  " + e.getMessage());
        }
    }

}
