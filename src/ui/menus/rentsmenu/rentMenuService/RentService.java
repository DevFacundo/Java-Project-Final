package ui.menus.rentsmenu.rentMenuService;

import model.State;
import model.clients.Owner;
import model.clients.Tenant;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Apartment;
import model.properties.Orientation;
import model.properties.Property;
import model.rents.Rent;
import model.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RentService {

    private GenericClass<Property> properties;
    private GenericClass<Rent> rents;
    private GenericClass<Tenant> tenants;
    private GenericClass<Owner> owners;
    private Scanner scanner;

    public RentService() {
        rents = new GenericClass<>(JsonUtils.loadList("rents.json", Rent.class));
        tenants = new GenericClass<>(JsonUtils.loadList("tenants.json", Tenant.class));
        properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
        scanner = new Scanner(System.in);
    }

    public void addRent() {
        Boolean continueAdding = true;
        do {
            try {
                rents = new GenericClass<>(JsonUtils.loadList("rents.json", Rent.class));
                tenants = new GenericClass<>(JsonUtils.loadList("tenants.json", Tenant.class));
                properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
                owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));


                Rent newRent = createRent();

                System.out.println("Rent created successfully!");
                System.out.println(newRent);

                if (!rents.isEmpty()) {
                    Rent r = rents.getLastObject();
                    Integer lastId = r.getId() + 1;
                    newRent.setId(lastId);
                }

                rents.addElement(newRent);

                JsonUtils.saveList(rents.returnList(), "rents.json", Rent.class);

            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (DuplicateElementException e) {
                System.out.println("Error: " + e.getMessage());
            }

            continueAdding = askToContinue();

        } while (continueAdding);
    }

    public Rent createRent() throws InvalidInputException {


        System.out.print("Enter the ID of the property to rent: ");
        Integer propertyId = Integer.parseInt(scanner.nextLine().trim());

        Property property = findPropertyById(propertyId);

        if (property == null || property.getState() != State.AVAILABLE) {
            throw new InvalidInputException("Property is not available for rent.");
        }

        System.out.print("Enter the tenant's DNI: ");
        String tenantDni = scanner.nextLine().trim();
        Tenant tenant = validateTenant(tenantDni);

        //VALIDAR FECHASSSSSSSSSSSSSSSSS
        System.out.print("Enter the rental start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        LocalDate rentalStartDate = LocalDate.parse(startDate);

        System.out.print("Enter the rental end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        LocalDate rentalEndDate = LocalDate.parse(endDate);
        dateValidation(rentalStartDate, rentalEndDate);

        ///SETTING THE CLIENTS STATE
        //SETTING THE TENANT STATE
        tenant.setClientState(State.RENTED);
        tenants.modifyElement(tenant, tenant);
        JsonUtils.saveList(tenants.returnList(), "tenants.json", Tenant.class);
        //SETTING THE OWNER STATE
        property.getOwner().setClientState(State.RENTED);
        owners.modifyElement(property.getOwner(), property.getOwner());
        JsonUtils.saveList(owners.returnList(), "owners.json", Owner.class);

        //SETTING THE PROPERTY STATE
        property.setState(State.RENTED);
        properties.modifyElement(property, property);
        JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);

        return new Rent(tenant, property, rentalStartDate, rentalEndDate);
    }

    private Property findPropertyById(Integer propertyId) {
        for (Property p : properties.returnList()) {
            if (p.getId().equals(propertyId)) {
                return p;
            }
        }
        return null;
    }

    private Tenant validateTenant(String tenantDni) throws InvalidInputException {
        for (Tenant t : tenants.returnList()) {
            if (t.getDni().equalsIgnoreCase(tenantDni)) {
                return t;
            }
        }
        throw new InvalidInputException("Tenant with DNI " + tenantDni + " not found.");
    }

    private Boolean askToContinue() {
        System.out.print("Do you want to add another rent? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }


    public void dateValidation(LocalDate fechaInicio, LocalDate fechaFin) throws InvalidInputException {
        if (fechaInicio == null || fechaFin == null) {
            throw new InvalidInputException("Las fechas no pueden ser nulas");
        }

        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new InvalidInputException("La fecha de inicio no puede ser anterior a hoy");
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new InvalidInputException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        if (fechaFin.isEqual(fechaInicio)) {
            throw new InvalidInputException("Las fechas de inicio y fin no pueden ser iguales");
        }
    }

    public void modifyRent() {
        Boolean continueModifying = true;
        do {
            try {
                rents = new GenericClass<>(JsonUtils.loadList("rents.json", Rent.class));
                System.out.print("Enter the ID of the rent to modify: ");
                Integer rentId = Integer.parseInt(scanner.nextLine().trim());

                Rent rentToModify = findRentById(rentId);

                if (rentToModify == null) {
                    throw new InvalidInputException("Rent with ID " + rentId + " not found.");
                }

                System.out.println(rentToModify);

                modifyRentDetails(rentToModify);

                JsonUtils.saveList(rents.returnList(), "rents.json", Rent.class);

                System.out.println("Rent modified successfully: " + rentToModify);

            } catch (InvalidInputException | NumberFormatException e) {
                System.out.println("Error modifying rent: " + e.getMessage());
            }

            continueModifying = askToContinue();
        } while (continueModifying);
    }

    public Rent findRentById(Integer rentId) {
        for (Rent rent : rents.returnList()) {
            if (rent instanceof Rent) {
                Rent rent1 = (Rent) rent;
                if (rent1.getId().equals(rentId)) {
                    return rent1;
                }
            }
        }
        return null;
    }

    public void modifyRentDetails(Rent rent) throws InvalidInputException {
        Boolean continueModifying = true;
        Integer option;

        do {
            System.out.println("\n----------------------------------------------------");
            System.out.println("     Modify Rent Details");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Tenants");
            System.out.println("2. Property");
            System.out.println("3. Start Rental Date ");
            System.out.println("4. End Rental Date");
            System.out.println("0. Go back");
            System.out.println("----------------------------------------------------");
            System.out.println("Please select the detail you would like to modify: ");

            option = Utils.getValidatedOption();

            switch (option) {
                case 1:
                    System.out.print("Actual Tenant Dni: (" +rent.getTenant().getDni() + ")\nNew Tenant Dni: ");
                    String tenantDni = scanner.nextLine().trim();
                    rent.setTenant(validateTenant(tenantDni));
                    break;

                case 2:
                    System.out.print("Actual Property ID: (" +rent.getProperty().getId() + ")\nNew Property ID: ");
                    Integer propertyId = Integer.parseInt(scanner.nextLine().trim());
                    Property property = (findPropertyById(propertyId));
                    if (property == null || property.getState() != State.AVAILABLE) {
                        throw new InvalidInputException("Property is not available for rent.");
                    }
                    else {rent.setProperty(property);}
                    break;

                case 3:
                    System.out.print("Enter the rental start date (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    LocalDate rentalStartDate = LocalDate.parse(startDate);
                    dateValidation(rentalStartDate, rent.getEndRent());
                    break;

                case 4:
                    System.out.print("Enter the rental end date (YYYY-MM-DD): ");
                    String endDate = scanner.nextLine();
                    LocalDate rentalEndDate = LocalDate.parse(endDate);
                    dateValidation(rent.getStartRent(), rentalEndDate);
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

    public void validateArea(Double area) throws InvalidInputException {
        if (area <= 0) {
            throw new InvalidInputException("Area must be greater than zero.");
        }
    }


}
