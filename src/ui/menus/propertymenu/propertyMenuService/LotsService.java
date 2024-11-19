package ui.menus.propertymenu.propertyMenuService;

import model.State;
import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.exceptions.RentedException;
import model.exceptions.SoldException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.House;
import model.properties.Lot;
import model.properties.Property;
import model.utils.Utils;


import java.util.Scanner;

public class LotsService {
    Scanner scanner;
    GenericClass<Property> properties;
    GenericClass<Owner> owners;

    public LotsService() {
        scanner = new Scanner(System.in);
        properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
    }

    public void addLot() {
        Boolean continueAdding = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
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

        return new Lot(owner, address, area, sp, electricity, water, sewer, asphalt, gas);
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

    public void modifyLot() {
        Boolean continueModifying = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));

                System.out.print("Enter the ID of the lot to modify: ");
                Integer lotId = Integer.parseInt(scanner.nextLine().trim());

                Lot lotToModify = findLotById(lotId);

                ///VALIDATIONS TO MODIFY
                if (lotToModify == null) {
                    throw new InvalidInputException("Lot with ID " + lotId + " not found.");
                }
                if (lotToModify.getState()== State.RENTED)
                {
                    throw new RentedException("You can't modify it because it has already rented");
                }

                if (lotToModify.getState()== State.SOLD)
                {
                    throw new SoldException("You can't modify it because it has already sold");
                }

                System.out.println(lotToModify);

                modifyLotDetails(lotToModify);

                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);

                System.out.println("Lot modified successfully: " + lotToModify);

            } catch (InvalidInputException | NumberFormatException e) {
                System.out.println("Error modifying lot: " + e.getMessage());
            } catch (SoldException e) {
                throw new RuntimeException(e);
            } catch (RentedException e) {
                throw new RuntimeException(e);
            }

            continueModifying = askToContinue();
        } while (continueModifying);
    }

    public Lot findLotById(Integer lotId) {
        for (Property property : properties.returnList()) {
            if (property instanceof Lot) {
                Lot lot = (Lot) property;
                if (lot.getId().equals(lotId)) {
                    return lot;
                }
            }
        }
        return null;
    }

    private void modifyLotDetails(Lot lot) throws InvalidInputException {
        Boolean continueModifying = true;
        Integer option;

        do {
            System.out.println("\n----------------------------------------------------");
            System.out.println("               Modify Lot Details");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Address");
            System.out.println("2. Area");
            System.out.println("3. Sales Price");
            System.out.println("4. Electricity (Yes/No)");
            System.out.println("5. Water (Yes/No)");
            System.out.println("6. Sewer (Yes/No)");
            System.out.println("7. Asphalt (Yes/No)");
            System.out.println("8. Gas (Yes/No)");
            System.out.println("0. Go back");
            System.out.println("----------------------------------------------------");
            System.out.println("Please select the detail you would like to modify:");

            option = Utils.getValidatedOption();

            switch (option) {
                case 1:
                    System.out.print("Address (" + lot.getAdress() + "): ");
                    String newAddress = scanner.nextLine().trim();
                    if (!newAddress.isEmpty()) {
                        lot.setAdress(newAddress);
                    }
                    break;

                case 2:
                    System.out.print("Area (" + lot.getArea() + "): ");
                    String newArea = scanner.nextLine().trim();
                    if (!newArea.isEmpty()) {
                        double area = Double.parseDouble(newArea);
                        validateArea(area);
                        lot.setArea(area);
                    }
                    break;

                case 3:
                    System.out.print("Sales Price (" + lot.getSalesPrice() + "): ");
                    String newSalesPrice = scanner.nextLine().trim();
                    if (!newSalesPrice.isEmpty()) {
                        double sp = Double.parseDouble(newSalesPrice);
                        validatePrice(sp);
                        lot.setSalesPrice(sp);
                    }
                    break;

                case 4:
                    modifyBooleanProperty("Electricity", lot.getElectricity(), lot::setElectricity);
                    break;

                case 5:
                    modifyBooleanProperty("Water", lot.getWater(), lot::setWater);
                    break;

                case 6:
                    modifyBooleanProperty("Sewer", lot.getSewer(), lot::setSewer);
                    break;

                case 7:
                    modifyBooleanProperty("Asphalt", lot.getAsphalt(), lot::setAsphalt);
                    break;

                case 8:
                    modifyBooleanProperty("Gas", lot.getGas(), lot::setGas);
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

    private void modifyBooleanProperty(String propertyName, Boolean currentValue, java.util.function.Consumer<Boolean> setter) {
        System.out.println(propertyName + " (" + (currentValue ? "Yes" : "No") + "): (Y/N)");
        String newValue = scanner.nextLine().trim();
        if (newValue.equalsIgnoreCase("Y")) {
            setter.accept(true);
        } else if (newValue.equalsIgnoreCase("N")) {
            setter.accept(false);
        } else {
            System.out.println("Invalid input. " + propertyName + " remains unchanged.");
        }
    }

    private void validateArea(Double area) throws InvalidInputException {
        if (area <= 0) {
            throw new InvalidInputException("Area must be greater than zero.");
        }
    }

    private void validatePrice(Double price) throws InvalidInputException {
        if (price <= 0) {
            throw new InvalidInputException("Price must be greater than zero.");
        }
    }
    public void deleteProperty() {
        try {
            properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
            if (properties.isEmpty()) {
                System.out.println("No Properties available to delete.");
                return;
            }

            System.out.print("Enter the ID of the property you want to delete: ");
            Integer propertyId = Integer.parseInt(scanner.nextLine().trim());

            Lot lotToDelete = findLotById(propertyId);

            ///VALIDATIONS FOR DELETE WITH EXCEPTIONS
            if (lotToDelete == null) {
                throw new InvalidInputException("Property with ID " + propertyId + " not found.");
            }
            if (lotToDelete.getState()== State.RENTED)
            {
                throw new RentedException("You can't delete it because it has already rented");
            }
            if (lotToDelete.getState()== State.SOLD)
            {
                throw new SoldException("You can't delete it because it has already sold");
            }
            System.out.println("Selected Property: " + lotToDelete);

            properties.deleteElement(lotToDelete);

            JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);
            System.out.println("Property deleted successfully!");
        } catch (InvalidInputException | SoldException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RentedException e) {
            System.out.println("Error: "+ e.getMessage());;
        }
    }
}
