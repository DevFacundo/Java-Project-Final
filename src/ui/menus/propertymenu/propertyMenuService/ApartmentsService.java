package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Apartment;
import model.properties.Orientation;
import model.properties.Property;

import java.util.Scanner;

public class ApartmentsService {

    Scanner scanner = new Scanner(System.in);
    GenericClass<Property> properties;
    GenericClass<Owner> owners;


    private void addApartament() {
        Boolean continueAdding = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owner.json", Owner.class));
                Apartment newApartment = createApartment(scanner, owners);

                System.out.println("Apartment added successfully:");
                System.out.println(newApartment);
                if (!properties.isEmpty()) {
                    Property p = properties.getLastObject();
                    Integer lastId = p.getId() + 1;
                    newApartment.setId(lastId);
                }
                properties.addElement(newApartment);
                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding apartment: " + e.getMessage());
            } catch (DuplicateElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
            continueAdding = askToContinue();
        } while (continueAdding);
    }

    public static Apartment createApartment(Scanner scanner, GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = new Owner();
        System.out.print("Enter the owner's DNI: ");
        String ownerDni = scanner.nextLine().trim();
        owner = validateOwner(ownerDni, ownerList);

        System.out.print("Enter apartment address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter area: ");
        Double area = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter sales Price: ");
        Double sp = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Rental Price: ");
        Double rp = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Enter rooms quantity: ");
        Integer roomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter bedrooms quantity: ");
        Integer bedroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter bathrooms quantity: ");
        Integer bathroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        Boolean furniture = null;
        do {
            System.out.println("the apartment has furniture?  (Y/N)");
            String flag = scanner.nextLine().trim();

            if (flag.equalsIgnoreCase("Y")) {
                furniture = true;
            } else if (flag.equalsIgnoreCase("N")) {
                furniture = false;
            }
        }
        while (furniture == null);

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
        while (flag != 1 || flag != 2);

        System.out.println("Enter the maintenance fees: ");
        Double maintenanceFees = Double.parseDouble(scanner.nextLine().trim());


        validateApartmentInputs(address, area, sp, rp, roomsQuantity, bathroomsQuantity, bedroomsQuantity,maintenanceFees);

        return new Apartment(owner, address, area, sp, rp, roomsQuantity, bathroomsQuantity, bedroomsQuantity,furniture,orientation,maintenanceFees);
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

    public static void validateApartmentInputs(String address, Double area, Double sp, Double rp, Integer roomsQuantity, Integer bathroomsQuantity, Integer bedroomsQuantity,Double maintenanceFees) throws InvalidInputException {
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
        if (roomsQuantity <= 0) {
            throw new InvalidInputException("Rooms Quantity must be a positive number.");
        }
        if (bedroomsQuantity <= 0) {
            throw new InvalidInputException("Redrooms Quantity must be a positive number.");
        }
        if (bathroomsQuantity <= 0) {
            throw new InvalidInputException("Bathrooms Quantity must be a positive number.");
        }
        if (maintenanceFees.isNaN() || maintenanceFees <= 0) {
            throw new InvalidInputException("Maintenance Fees must be a positive number.");
        }
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another Apartment? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }


}
