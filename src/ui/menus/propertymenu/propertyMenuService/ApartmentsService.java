package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Apartment;
import model.properties.Orientation;
import model.properties.Property;
import model.utils.Utils;

import java.util.Scanner;

public class ApartmentsService {

    Scanner scanner;
    GenericClass<Property> properties;
    GenericClass<Owner> owners;

    public ApartmentsService() {
        scanner = new Scanner(System.in);
        properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
    }

    public void addApartament() {
        Boolean continueAdding = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
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

        int flag = 0;
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

        System.out.println("Enter the maintenance fees: ");
        Double maintenanceFees = Double.parseDouble(scanner.nextLine().trim());


        validateApartmentInputs(address, area, sp, rp, roomsQuantity, bathroomsQuantity, bedroomsQuantity, maintenanceFees);

        return new Apartment(owner, address, area, sp, rp, roomsQuantity, bathroomsQuantity, bedroomsQuantity, furniture, orientation, maintenanceFees);
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

    public static void validateApartmentInputs(String address, Double area, Double sp, Double rp, Integer roomsQuantity, Integer bathroomsQuantity, Integer bedroomsQuantity, Double maintenanceFees) throws InvalidInputException {
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

    public void modifyApartment() {
        Boolean continueModifying = true;
        do {
            try {
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
                System.out.print("Enter the ID of the apartment to modify: ");
                Integer apartmentId = Integer.parseInt(scanner.nextLine().trim());

                Apartment apartmentToModify = findApartmentById(apartmentId);

                if (apartmentToModify == null) {
                    throw new InvalidInputException("Apartment with ID " + apartmentId + " not found.");
                }

                System.out.println(apartmentToModify);

                modifyApartmentDetails(apartmentToModify);

                JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);

                System.out.println("Apartment modified successfully: " + apartmentToModify);

            } catch (InvalidInputException | NumberFormatException e) {
                System.out.println("Error modifying apartment: " + e.getMessage());
            }

            continueModifying = askToContinue();
        } while (continueModifying);
    }

    public Apartment findApartmentById(Integer apartmentId) {
        for (Property property : properties.returnList()) {
            if (property instanceof Apartment) {
                Apartment apartment = (Apartment) property;
                if (apartment.getId().equals(apartmentId)) {
                    return apartment;
                }
            }
        }
        return null;
    }

    public void modifyApartmentDetails(Apartment apartment) throws InvalidInputException {
        Boolean continueModifying = true;
        Integer option;

        do {
            System.out.println("\n----------------------------------------------------");
            System.out.println("     Modify Apartment Details");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Address");
            System.out.println("2. Area");
            System.out.println("3. Sales Price");
            System.out.println("4. Rental Price");
            System.out.println("5. Rooms Quantity");
            System.out.println("6. Bedrooms Quantity");
            System.out.println("7. Bathrooms Quantity");
            System.out.println("8. Furniture");
            System.out.println("9. Orientation");
            System.out.println("10. Maintenance Fees");
            System.out.println("0. Go back");
            System.out.println("----------------------------------------------------");
            System.out.println("Please select the detail you would like to modify: ");

            option = Utils.getValidatedOption();

            switch (option) {
                case 1:
                    System.out.print("Address (" + apartment.getAdress() + "): ");
                    String newAddress = scanner.nextLine().trim();
                    if (!newAddress.isEmpty()) {
                        Utils.validateAddress(newAddress);
                        apartment.setAdress(newAddress);
                    }
                    break;

                case 2:
                    System.out.print("Area (" + apartment.getArea() + "): ");
                    String newArea = scanner.nextLine().trim();
                    if (!newArea.isEmpty()) {
                        double area = Double.parseDouble(newArea);
                        Utils.validateArea(area);
                        apartment.setArea(area);
                    }
                    break;

                case 3:
                    System.out.print("Sales Price (" + apartment.getSalesPrice() + "): ");
                    String newSalesPrice = scanner.nextLine().trim();
                    if (!newSalesPrice.isEmpty()) {
                        double sp = Double.parseDouble(newSalesPrice);
                        Utils.validatePrice(sp);
                        apartment.setSalesPrice(sp);
                    }
                    break;

                case 4:
                    System.out.print("Rental Price (" + apartment.getRentalPrice() + "): ");
                    String newRentalPrice = scanner.nextLine().trim();
                    if (!newRentalPrice.isEmpty()) {
                        double rp = Double.parseDouble(newRentalPrice);
                        Utils.validatePrice(rp);
                        apartment.setRentalPrice(rp);
                    }
                    break;

                case 5:
                    System.out.print("Rooms Quantity (" + apartment.getRooms() + "): ");
                    String newRoomsQuantity = scanner.nextLine().trim();
                    if (!newRoomsQuantity.isEmpty()) {
                        int roomsQuantity = Integer.parseInt(newRoomsQuantity);
                        Utils.validateQuantity(roomsQuantity);
                        apartment.setRooms(roomsQuantity);
                    }
                    break;

                case 6:
                    System.out.print("Bedrooms Quantity (" + apartment.getBedRooms() + "): ");
                    String newBedroomsQuantity = scanner.nextLine().trim();
                    if (!newBedroomsQuantity.isEmpty()) {
                        Integer bedroomsQuantity = Integer.parseInt(newBedroomsQuantity);
                        Utils.validateQuantity(bedroomsQuantity);
                        apartment.setBedRooms(bedroomsQuantity);
                    }
                    break;

                case 7:
                    System.out.print("Bathrooms Quantity (" + apartment.getBathRooms() + "): ");
                    String newBathroomsQuantity = scanner.nextLine().trim();
                    if (!newBathroomsQuantity.isEmpty()) {
                        int bathroomsQuantity = Integer.parseInt(newBathroomsQuantity);
                        Utils.validateQuantity(bathroomsQuantity);
                        apartment.setBathRooms(bathroomsQuantity);
                    }
                    break;

                case 8:
                    System.out.print("Furniture (" + (apartment.getFurnished() ? "Yes" : "No") + "): ");
                    String newFurniture = scanner.nextLine().trim();
                    if (!newFurniture.isEmpty()) {
                        apartment.setFurnished(newFurniture.equalsIgnoreCase("Y"));
                    }
                    break;

                case 9:
                    System.out.print("Orientation (" + apartment.getOrientation() + "): ");
                    String newOrientation = scanner.nextLine().trim();
                    if (!newOrientation.isEmpty()) {
                        apartment.setOrientation(Orientation.valueOf(newOrientation.toUpperCase()));
                    }
                    break;

                case 10:
                    System.out.print("Maintenance Fees (" + apartment.getMaintenanceFees() + "): ");
                    String newMaintenanceFees = scanner.nextLine().trim();
                    if (!newMaintenanceFees.isEmpty()) {
                        double maintenanceFees = Double.parseDouble(newMaintenanceFees);
                        Utils.validatePrice(maintenanceFees);
                        apartment.setMaintenanceFees(maintenanceFees);
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
}
