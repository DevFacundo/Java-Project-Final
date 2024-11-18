package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Property;
import model.properties.TypeOfUse;
import model.properties.WareHouse;

import java.util.Scanner;

public class WarehousesServices {

    Scanner scanner;
    GenericClass<Property> properties;
    GenericClass<Owner> owners;

    public WarehousesServices() {
        scanner = new Scanner(System.in);
        properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
    }

<<<<<<< HEAD
   public void addWarehouse() {
=======
    public void addWarehouse() {
>>>>>>> d7ba2119c65bdc474eff631e005100a861f71766
        Boolean continueAdding = true;
        do {
            try {
                WareHouse newWareHouse = createWareHouse(scanner, owners);

                System.out.println("WareHouse added successfully:");
                System.out.println(newWareHouse);
                if (!properties.isEmpty()) {
                    Property p = properties.getLastObject();
                    Integer lastId = p.getId() + 1;
                    newWareHouse.setId(lastId);
                }
                properties.addElement(newWareHouse);
                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding WareHouse: " + e.getMessage());
            } catch (DuplicateElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
            continueAdding = askToContinue();
        } while (continueAdding);
    }

    public static WareHouse createWareHouse(Scanner scanner, GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = new Owner();
        System.out.print("Enter the owner's DNI: ");
        String ownerDni = scanner.nextLine().trim();
        owner = validateOwner(ownerDni, ownerList);

        System.out.print("Enter WareHouse address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter area: ");
        Double area = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter sales Price: ");
        Double sp = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Rental Price: ");
        Double rp = Double.parseDouble(scanner.nextLine().trim());

        int flag = 0;
        TypeOfUse typeOfUse = null;
        do {
            System.out.println("what is the use of the warehouse ? (1.STORAGE / 2. INDUSTRIAL / 3. COMMERCIAL / 4. WORKSHOP / 5. EVENTS)");
            flag = Integer.parseInt(scanner.nextLine().trim());
            if (flag == 1) {
                typeOfUse = TypeOfUse.STORAGE;
            } else if (flag == 2) {
                typeOfUse = TypeOfUse.INDUSTRIAL;
            } else if (flag == 3) {
                typeOfUse = TypeOfUse.COMMERCIAL;
            } else if (flag == 4) {
                typeOfUse = TypeOfUse.WORKSHOP;
            } else if (flag == 5) {
                typeOfUse = TypeOfUse.EVENTS;
            }
        }
        while (flag != 1 && flag != 2 && flag != 3 && flag != 4 && flag != 5);

        System.out.println("Enter bathrooms quantity: ");
        Integer bathroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter floors quantity");
        Integer floorsQuantity = Integer.parseInt(scanner.nextLine().trim());


        validateWareHouseInputs(address, area, sp, rp, bathroomsQuantity,floorsQuantity);

        return new WareHouse(owner, address, area, sp, rp, typeOfUse, bathroomsQuantity,floorsQuantity);
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

    public static void validateWareHouseInputs(String address, Double area, Double sp, Double rp, Integer bathroomsQuantity,Integer floor) throws InvalidInputException {
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
        if (floor <= 0){
            throw new InvalidInputException("Floor must be a positive number.");
        }
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another WareHouse? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }


}
