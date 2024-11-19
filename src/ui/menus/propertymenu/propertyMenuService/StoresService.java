package ui.menus.propertymenu.propertyMenuService;

import model.State;
import model.clients.Owner;
import model.exceptions.*;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.*;
import model.utils.Utils;

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
        Boolean continueAdding;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
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
        Owner owner;
        System.out.print("Enter the owner's DNI: ");
        String ownerDni = scanner.nextLine().trim();
        owner = validateOwner(ownerDni, ownerList);

        System.out.print("Enter Store address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter area: ");
        Double area;
        try {
            area = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The area must be a valid number.");
        }

        System.out.print("Enter sales Price: ");
        Double sp;
        try {
            sp = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The Sales Price must be a valid number.");
        }


        System.out.print("Enter Rental Price: ");
        Double rp;
        try {
            rp = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The Sales Price must be a valid number.");
        }

        System.out.println("Enter bathrooms quantity: ");
        Integer bathroomsQuantity;
        try {
            bathroomsQuantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The bathrooms quantity must be a valid number.");
        }

        int flag;
        Orientation orientation = null;
        do {
            System.out.println("what is the orientation? (1.FRONT / 2. BACK)");
            flag = Integer.parseInt(scanner.nextLine().trim());
            if (flag == 1) {
                orientation = Orientation.FRONT;
            } else if (flag == 2) {
                orientation = Orientation.BACK;
            }
        }
        while (flag != 1 && flag != 2);

        System.out.println("Enter floors quantity");
        Integer floors;
        try {
            floors = Integer.parseInt(scanner.nextLine().trim());
        }catch (NumberFormatException e) {
            throw new InvalidInputException("The Floors quantity must be a valid number.");
        }


        validateStoreInputs(address, area, sp, rp, bathroomsQuantity, floors);

        return new Store(owner, address, area, sp, rp, bathroomsQuantity, orientation, floors);
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

    public static void validateStoreInputs(String address, Double area, Double sp, Double rp, Integer bathroomsQuantity, Integer floors) throws InvalidInputException {
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
        if (floors < 0) {
            throw new InvalidInputException("Floors quantity must be a positive number or null.");
        }
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another Store? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    public void modifyStore() {
        Boolean continueModifying;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));

                System.out.print("Enter the ID of the store to modify: ");
                Integer storeId = Integer.parseInt(scanner.nextLine().trim());

                Store storeToModify = findStoreById(storeId);

                ///VALIDATIONS TO MODIFY WITH EXCEPTIONS
                if (storeToModify == null) {
                    throw new InvalidInputException("Store with ID " + storeId + " not found.");
                }
                if (storeToModify.getState()== State.RENTED)
                {
                    throw new RentedException("You can't modify it because it has already rented");
                }
                if (storeToModify.getState()== State.SOLD)
                {
                    throw new SoldException("You can't modify it because it has already sold");
                }


                System.out.println(storeToModify);

                modifyStoreDetails(storeToModify);

                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);

                System.out.println("Store modified successfully: " + storeToModify);

            } catch (InvalidInputException | NumberFormatException e) {
                System.out.println("Error modifying store: " + e.getMessage());
            } catch (SoldException e) {
                System.out.println("Error: "+e.getMessage());
            } catch (RentedException e) {
                System.out.println("Error:  "+e.getMessage());
            }

            continueModifying = askToContinue();
        } while (continueModifying);
    }

    private Store findStoreById(Integer storeId) {
        for (Property property : properties.returnList()) {
            if (property instanceof Store) {
                Store store = (Store) property;
                if (store.getId().equals(storeId)) {
                    return store;
                }
            }
        }
        return null;
    }

    private void modifyStoreDetails(Store store) throws InvalidInputException {
        Boolean continueModifying;
        Integer option;

        do {
            System.out.println("\n----------------------------------------------------");
            System.out.println("               Modify Store Details");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Address");
            System.out.println("2. Area");
            System.out.println("3. Sales Price");
            System.out.println("4. Rental Price");
            System.out.println("5. Bathrooms Quantity");
            System.out.println("6. Orientation (FRONT/BACK)");
            System.out.println("7. Floors Quantity");
            System.out.println("0. Go back");
            System.out.println("----------------------------------------------------");
            System.out.println("Please select the detail you would like to modify:");

            option = Utils.getValidatedOption();

            switch (option) {
                case 1:
                    System.out.print("Address (" + store.getAdress() + "): ");
                    String newAddress = scanner.nextLine().trim();
                    if (!newAddress.isEmpty()) {
                        store.setAdress(newAddress);
                    }
                    break;

                case 2:
                    System.out.print("Area (" + store.getArea() + "): ");
                    String newArea = scanner.nextLine().trim();
                    if (!newArea.isEmpty()) {
                        Double area = Double.parseDouble(newArea);
                        Utils.validateArea(area);
                        store.setArea(area);
                    }
                    break;

                case 3:
                    System.out.print("Sales Price (" + store.getSalesPrice() + "): ");
                    String newSalesPrice = scanner.nextLine().trim();
                    if (!newSalesPrice.isEmpty()) {
                        Double sp = Double.parseDouble(newSalesPrice);
                        Utils.validatePrice(sp);
                        store.setSalesPrice(sp);
                    }
                    break;

                case 4:
                    System.out.print("Rental Price (" + store.getRentalPrice() + "): ");
                    String newRentalPrice = scanner.nextLine().trim();
                    if (!newRentalPrice.isEmpty()) {
                        Double rp = Double.parseDouble(newRentalPrice);
                        Utils.validatePrice(rp);
                        store.setRentalPrice(rp);
                    }
                    break;

                case 5:
                    System.out.print("Bathrooms Quantity (" + store.getBathRooms() + "): ");
                    String newBathrooms = scanner.nextLine().trim();
                    if (!newBathrooms.isEmpty()) {
                        Integer bathrooms = Integer.parseInt(newBathrooms);
                        Utils.validateBathrooms(bathrooms);
                        store.setBathRooms(bathrooms);
                    }
                    break;

                case 6:
                    System.out.print("Orientation (FRONT/BACK) (" + store.getOrientation() + "): ");
                    String newOrientation = scanner.nextLine().trim().toUpperCase();
                    if (newOrientation.equals("FRONT") || newOrientation.equals("BACK")) {
                        store.setOrientation(Orientation.valueOf(newOrientation));
                    } else {
                        System.out.println("Invalid orientation. Value remains unchanged.");
                    }
                    break;

                case 7:
                    System.out.print("Floors Quantity (" + store.getFloorsQuantity() + "): ");
                    String newFloors = scanner.nextLine().trim();
                    if (!newFloors.isEmpty()) {
                        int floors = Integer.parseInt(newFloors);
                        Utils.validateFloors(floors);
                        store.setFloorsQuantity(floors);
                    }
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

    public void deleteProperty() {
        try {
            properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
            if (properties.isEmpty()) {
                System.out.println("No Properties available to delete.");
                return;
            }

            Integer propertyId;
            try {
                System.out.print("Enter the ID of the property you want to delete: ");
                propertyId = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new InvalidInputException("The Id must be a valid number.");
            }

            Store storeToDelete = findStoreById(propertyId);

            ///VALIDATIONS FOR DELETE WITH EXCEPTIONS
            if (storeToDelete == null) {
                throw new InvalidInputException("Property with ID " + propertyId + " not found.");
            }
            if (storeToDelete.getState()== State.RENTED)
            {
                throw new RentedException("You can't delete it because it has already rented");
            }
            if (storeToDelete.getState()== State.SOLD)
            {
                throw new SoldException("You can't delete it because it has already sold");
            }
            System.out.println("Selected Property: " + storeToDelete);

            properties.deleteElement(storeToDelete);

            JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);
            System.out.println("Property deleted successfully!");
        } catch (InvalidInputException | SoldException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RentedException e) {
            System.out.println("Error:  "+ e.getMessage());
        }
    }
    public void seeAllStores() throws ElementNotFoundException {
        Integer counter=0;
        if (properties.isEmpty()) {
            throw new ElementNotFoundException("No properties found.");
        }
        for (Property p : properties.returnList())
        {
            if (p instanceof Store)
            {
                counter++;
                System.out.println(p);
            }
        }
        if (counter == 0)
        {
            System.out.println("Not Stores found");
        }
    }
}