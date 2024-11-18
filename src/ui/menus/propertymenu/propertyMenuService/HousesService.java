package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.House;
import model.properties.Property;

import java.util.Scanner;

public class HousesService {
    Scanner scanner = new Scanner(System.in);
    GenericClass<Property> properties;
    GenericClass<Owner> owners;

    public HousesService() {
        scanner = new Scanner(System.in);
        properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
    }

    public void addHouse() {
        Boolean continueAdding = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
                House newHouse = createHouse(scanner, owners);

                System.out.println("House added successfully:");
                System.out.println(newHouse);

                if (!properties.isEmpty()) {
                    Property p = properties.getLastObject();
                    Integer lastId = p.getId() + 1;
                    newHouse.setId(lastId);
                }

                properties.addElement(newHouse);
                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);

            } catch (InvalidInputException e) {
                System.out.println("Error adding house: " + e.getMessage());
            } catch (DuplicateElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
            continueAdding = askToContinue();
        } while (continueAdding);
    }

    public static House createHouse(Scanner scanner, GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = new Owner();
        System.out.print("Enter the owner's DNI: ");
        String ownerDni = scanner.nextLine().trim();
        owner = validateOwner(ownerDni, ownerList);

        System.out.print("Enter house address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter area: ");
        Double area = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter sales Price: ");
        Double sp = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Rental Price: ");
        Double rp = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Enter floors quantity");
        Integer floorsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter rooms quantity");
        Integer roomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter bedrooms quantity");
        Integer bedroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter bathrooms quantity");
        Integer bathroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        Boolean park = null;
        do{
        System.out.println("The house have park? (Y/N)");
        String flag = scanner.nextLine().trim();

        if (flag.equalsIgnoreCase("Y")) {
            park = true;
        }
        else if (flag.equalsIgnoreCase("N")) {
            park = false;
        }
        }
        while(park==null);

        validateHouseInputs(address, area, sp, rp, floorsQuantity, roomsQuantity, bedroomsQuantity, bathroomsQuantity);

        return new House(owner, address, area, sp, rp, floorsQuantity, roomsQuantity, bedroomsQuantity, bathroomsQuantity, park);
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

    public static void validateHouseInputs(String address, Double area, Double sp, Double rp, Integer floorsQuantity, Integer roomsQuantity, Integer bedroomsQuantity, Integer bathroomsQuantity) throws InvalidInputException {
        if (address.isEmpty()) {
            throw new InvalidInputException("address cannot be empty.");
        }
        if (area.isNaN()) {
            throw new InvalidInputException("area is not a number.");
        }
        if (sp.isNaN()) {
            throw new InvalidInputException("Sales price is not a number.");
        }
        if (rp.isNaN()) {
            throw new InvalidInputException("Rental price is not a number.");
        }
        if (floorsQuantity < 0) {
            throw new InvalidInputException("Floors quantity must be a positive number.");
        }
        if (roomsQuantity <= 0) {
            throw new InvalidInputException("Rooms Quantity must be a positive number.");
        }
        if (bedroomsQuantity < 0) {
            throw new InvalidInputException("Redrooms Quantity must be a positive number.");
        }
        if (bathroomsQuantity < 0) {
            throw new InvalidInputException("Bathrooms Quantity must be a positive number.");
        }
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another house? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

}
