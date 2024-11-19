package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Property;
import model.properties.TypeOfUse;
import model.properties.WareHouse;
import model.utils.Utils;

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


    public void addWarehouse() {

        Boolean continueAdding = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
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
        if (bathroomsQuantity < 0) {
            throw new InvalidInputException("Bathrooms Quantity must be a positive number.");
        }
        if (floor < 0){
            throw new InvalidInputException("Floor must be a positive number.");
        }
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another WareHouse? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    public void modifyWarehouse() {
        Boolean continueModifying = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));

                System.out.print("Enter the ID of the Warehouse to modify: ");
                Integer warehouseId = Integer.parseInt(scanner.nextLine().trim());

                WareHouse warehouseToModify = findWarehouseById(warehouseId);

                if (warehouseToModify == null) {
                    throw new InvalidInputException("Warehouse with ID " + warehouseId + " not found.");
                }

                System.out.println(warehouseToModify);

                modifyWarehouseDetails(warehouseToModify);

                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);

                System.out.println("Warehouse modified successfully: " + warehouseToModify);

            } catch (InvalidInputException | NumberFormatException e) {
                System.out.println("Error modifying warehouse: " + e.getMessage());
            }

            continueModifying = askToContinue();
        } while (continueModifying);
    }

    private WareHouse findWarehouseById(Integer warehouseId) {
        for (Property property : properties.returnList()) {
            if (property instanceof WareHouse) {
                WareHouse warehouse = (WareHouse) property;
                if (warehouse.getId().equals(warehouseId)) {
                    return warehouse;
                }
            }
        }
        return null;
    }

    private void modifyWarehouseDetails(WareHouse warehouse) throws InvalidInputException {
        Boolean continueModifying = true;
        Integer option;

        do {
            System.out.println("\n----------------------------------------------------");
            System.out.println("               Modify Warehouse Details");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Address");
            System.out.println("2. Area");
            System.out.println("3. Sales Price");
            System.out.println("4. Rental Price");
            System.out.println("5. Bathrooms Quantity");
            System.out.println("6. Floors Quantity");
            System.out.println("7. Type of Use");
            System.out.println("0. Go back");
            System.out.println("----------------------------------------------------");
            System.out.println("Please select the detail you would like to modify:");

            option = Utils.getValidatedOption();

            switch (option) {
                case 1:
                    System.out.print("Address (" + warehouse.getAdress() + "): ");
                    String newAddress = scanner.nextLine().trim();
                    if (!newAddress.isEmpty()) {
                        warehouse.setAdress(newAddress);
                    }
                    break;

                case 2:
                    System.out.print("Area (" + warehouse.getArea() + "): ");
                    String newArea = scanner.nextLine().trim();
                    if (!newArea.isEmpty()) {
                        Double area = Double.parseDouble(newArea);
                        Utils.validateArea(area);
                        warehouse.setArea(area);
                    }
                    break;

                case 3:
                    System.out.print("Sales Price (" + warehouse.getSalesPrice() + "): ");
                    String newSalesPrice = scanner.nextLine().trim();
                    if (!newSalesPrice.isEmpty()) {
                        Double sp = Double.parseDouble(newSalesPrice);
                        Utils.validatePrice(sp);
                        warehouse.setSalesPrice(sp);
                    }
                    break;

                case 4:
                    System.out.print("Rental Price (" + warehouse.getRentalPrice() + "): ");
                    String newRentalPrice = scanner.nextLine().trim();
                    if (!newRentalPrice.isEmpty()) {
                        Double rp = Double.parseDouble(newRentalPrice);
                        Utils.validatePrice(rp);
                        warehouse.setRentalPrice(rp);
                    }
                    break;

                case 5:
                    System.out.print("Bathrooms Quantity (" + warehouse.getBathRooms() + "): ");
                    String newBathrooms = scanner.nextLine().trim();
                    if (!newBathrooms.isEmpty()) {
                        Integer bathrooms = Integer.parseInt(newBathrooms);
                        Utils.validateBathrooms(bathrooms);
                        warehouse.setBathRooms(bathrooms);
                    }
                    break;

                case 6:
                    System.out.print("Floors Quantity (" + warehouse.getFloorsQuantity() + "): ");
                    String newFloors = scanner.nextLine().trim();
                    if (!newFloors.isEmpty()) {
                        Integer floors = Integer.parseInt(newFloors);
                        Utils.validateFloors(floors);
                        warehouse.setFloorsQuantity(floors);
                    }
                    break;

                case 7:
                    System.out.print("Type of Use (" + warehouse.getTypeOfUse() + "): ");
                    System.out.println("1. STORAGE / 2. INDUSTRIAL / 3. COMMERCIAL / 4. WORKSHOP / 5. EVENTS");
                    Integer useType = Integer.parseInt(scanner.nextLine().trim());
                    warehouse.setTypeOfUse(TypeOfUse.values()[useType - 1]);
                    break;

                case 0:
                    System.out.println("Returning to the previous menu...");
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid number.");
                    break;
            }

            if (option != 0) {
                System.out.print("Do you want to modify another detail? (Y/N): ");
                String continueResponse = scanner.nextLine().trim();
                continueModifying = continueResponse.equalsIgnoreCase("Y");
            } else {
                continueModifying = false;
            }

        } while (continueModifying);
    }

}
