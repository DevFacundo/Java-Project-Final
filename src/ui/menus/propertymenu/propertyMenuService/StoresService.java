package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Orientation;
import model.properties.Property;
import model.properties.Store;

import java.util.Scanner;

public class StoresService {

    Scanner scanner;
    GenericClass<Property> properties;
    GenericClass<Owner> owners;

    public StoresService() {
        scanner = new Scanner(System.in);
        properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
    }

    public void addStore() {
        Boolean continueAdding = true;
        do {
            try {

                Store newStore = createStore(scanner, owners);

                System.out.println("Store added successfully:");
                System.out.println(newStore);
                if (!properties.isEmpty()) {
                    Property p = properties.getLastObject();
                    Integer lastId = p.getId() + 1;
                    newStore.setId(lastId);
                }
                properties.addElement(newStore);
                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding Store: " + e.getMessage());
            } catch (DuplicateElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
            continueAdding = askToContinue();
        } while (continueAdding);
    }

    public static Store createStore(Scanner scanner, GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = new Owner();
        System.out.print("Enter the owner's DNI: ");
        String ownerDni = scanner.nextLine().trim();
        owner = validateOwner(ownerDni, ownerList);

        System.out.print("Enter Store address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter area: ");
        Double area = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter sales Price: ");
        Double sp = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Rental Price: ");
        Double rp = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Enter bathrooms quantity: ");
        Integer bathroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        int flag=0;
        Orientation orientation = null;
        do {
            System.out.println("what is the orientation? (1.FRONT / 2. BACK)");
            flag = Integer.parseInt(scanner.nextLine().trim());
            if (flag == 1) {
                orientation = Orientation.FRONT;
            }
            else if (flag == 2) {
                orientation = Orientation.BACK;
            }
        }
        while (flag != 1 && flag != 2);

        System.out.println("Enter floors quantity: ");
        Integer floors = Integer.parseInt(scanner.nextLine().trim());


        validateStoreInputs(address, area, sp, rp, bathroomsQuantity,floors);

        return new Store(owner, address, area, sp, rp, bathroomsQuantity,orientation,floors);
    }

    public static Owner validateOwner(String ownerDni, GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = null;
        for (Owner ow : ownerList.returnList()) {
            if (ow.getDni().equalsIgnoreCase(ownerDni)) {
                owner = ow;
                break;
            }
        }
        if (owner == null) {
            throw new InvalidInputException("Owner with DNI " + ownerDni + " not found.");
        } else {
            return owner;
        }
    }

    public static void validateStoreInputs(String address, Double area, Double sp, Double rp, Integer bathroomsQuantity,Integer floors) throws InvalidInputException {
        if (address.isEmpty()) {
            throw new InvalidInputException("address cannot be empty.");
        }
        if (area.isNaN() || area <= 0) {
            throw new InvalidInputException("area is not a number.");
        }
        if (sp.isNaN() || sp <= 0) {
            throw new InvalidInputException("Sales price is not a number.");
        }
        if (rp.isNaN() || rp <= 0) {
            throw new InvalidInputException("Rental price is not a number.");
        }
        if (bathroomsQuantity <= 0) {
            throw new InvalidInputException("Bathrooms Quantity must be a positive number.");
        }
        if (floors < 0){
            throw new InvalidInputException("Floors quantity must be a positive number or null.");
        }
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another Store? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }


}
