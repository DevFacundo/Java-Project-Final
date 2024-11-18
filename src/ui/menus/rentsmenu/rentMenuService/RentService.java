package ui.menus.rentsmenu.rentMenuService;

import model.clients.Tenant;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.Property;
import model.properties.StateOfProperty;
import model.rents.Rent;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RentService {

    private GenericClass<Property> properties;
    private GenericClass<Rent> rents;
    private GenericClass<Tenant> tenants;
    private Scanner scanner = new Scanner(System.in);


    public void addRent() {
        Boolean continueAdding = true;
        do {
            try {
                Rent newRent = createRent();

                rents.addElement(newRent);

                JsonUtils.saveList(rents.returnList(), "rents.json", Rent.class);

                System.out.println("Rent created successfully!");
                System.out.println(newRent);

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


        if (property == null || property.getState() != StateOfProperty.AVAILABLE) {
            throw new InvalidInputException("Property is not available for rent.");
        }

        System.out.print("Enter the tenant's DNI: ");
        String tenantDni = scanner.nextLine().trim();
        Tenant tenant = validateTenant(tenantDni);

        // Solicitar las fechas de inicio y fin del alquiler
        System.out.print("Enter the rental start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        LocalDate rentalStartDate = LocalDate.parse(startDate);

        System.out.print("Enter the rental end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        LocalDate rentalEndDate = LocalDate.parse(endDate);

        property.setState(StateOfProperty.RENTED);
        properties.modifyElement(property, property);
        JsonUtils.saveList(properties.returnList(), "houses.json", Property.class);


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

}
