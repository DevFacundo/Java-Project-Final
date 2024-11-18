package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Lot;
import model.properties.Property;


import java.util.Scanner;

public class LotsService {
    Scanner scanner = new Scanner(System.in);
    GenericClass<Property> properties;
    GenericClass<Owner> owners;


    private void addLot() {
        Boolean continueAdding = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owner.json", Owner.class));
                Lot newLot = createLot(scanner, owners);

                System.out.println("Lot added successfully:");
                System.out.println(newLot);
                if (!properties.isEmpty()) {
                    Property p = properties.getLastObject();
                    Integer lastId = p.getId() + 1;
                    newLot.setId(lastId);
                }
                properties.addElement(newLot);
                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding Lot: " + e.getMessage());
            } catch (DuplicateElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
            continueAdding = askToContinue();
        } while (continueAdding);
    }

    public static Lot createLot(Scanner scanner, GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = new Owner();
        System.out.print("Enter the owner's DNI: ");
        String ownerDni = scanner.nextLine().trim();
        owner = validateOwner(ownerDni, ownerList);

        System.out.print("Enter Lot address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter area: ");
        Double area = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter sales Price: ");
        Double sp = Double.parseDouble(scanner.nextLine().trim());

        Boolean electricity = null;
        do {
            System.out.println("the Lot has electricity?  (Y/N)");
            String flag = scanner.nextLine().trim();

            if (flag.equalsIgnoreCase("Y")) {
                electricity = true;
            } else if (flag.equalsIgnoreCase("N")) {
                electricity = false;
            }
        }
        while (electricity == null);

        Boolean water = null;
        do {
            System.out.println("the Lot has water?  (Y/N)");
            String flag = scanner.nextLine().trim();

            if (flag.equalsIgnoreCase("Y")) {
                water = true;
            } else if (flag.equalsIgnoreCase("N")) {
                water = false;
            }
        }
        while (water == null);

        Boolean sewer = null;
        do {
            System.out.println("the Lot has sewer?  (Y/N)");
            String flag = scanner.nextLine().trim();

            if (flag.equalsIgnoreCase("Y")) {
                sewer = true;
            } else if (flag.equalsIgnoreCase("N")) {
                sewer = false;
            }
        }
        while (sewer == null);

        Boolean asphalt = null;
        do {
            System.out.println("the Lot has asphalt?  (Y/N)");
            String flag = scanner.nextLine().trim();

            if (flag.equalsIgnoreCase("Y")) {
                asphalt = true;
            } else if (flag.equalsIgnoreCase("N")) {
                asphalt = false;
            }
        }
        while (asphalt == null);

        Boolean gas = null;
        do {
            System.out.println("the Lot has gas?  (Y/N)");
            String flag = scanner.nextLine().trim();

            if (flag.equalsIgnoreCase("Y")) {
                gas = true;
            } else if (flag.equalsIgnoreCase("N")) {
                gas = false;
            }
        }
        while (gas == null);

        validateLotInputs(address, area, sp);

        return new Lot(owner, address, area, sp,electricity,water,sewer,asphalt,gas);
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

    public static void validateLotInputs(String address, Double area, Double sp) throws InvalidInputException {
        if (address.isEmpty()) {
            throw new InvalidInputException("address cannot be empty.");
        }
        if (area.isNaN() || area <= 0) {
            throw new InvalidInputException("area is not a number.");
        }
        if (sp.isNaN() || sp <= 0) {
            throw new InvalidInputException("Sales price is not a number.");
        }
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another Lot? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}
