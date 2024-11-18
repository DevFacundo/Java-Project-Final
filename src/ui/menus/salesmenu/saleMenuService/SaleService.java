package ui.menus.salesmenu.saleMenuService;

import model.clients.Buyer;
import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.House;
import model.properties.Property;
import model.properties.StateOfProperty;
import model.sales.Sale;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SaleService {
    private GenericClass<Property> properties;
    private GenericClass<Sale> sales;
    private GenericClass<Buyer>buyers;
    private Scanner scanner= new Scanner(System.in);

    public void addSale() {
        Boolean continueAdding = true;
        do {
            try {
                Sale newSale = createSale();

                sales.addElement(newSale);

                JsonUtils.saveList(sales.returnList(), "sales.json", Sale.class);

                System.out.println("Sale created successfully!");
                System.out.println(newSale);

            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch(DateTimeParseException e){
                System.out.println(("Error: "+ e.getMessage()));
            }
            catch (DuplicateElementException e)
            {
                System.out.println("Error: "+ e.getMessage());
            }

            continueAdding = askToContinue();

        } while (continueAdding);
    }


    public Sale createSale() throws  InvalidInputException {

        System.out.print("Enter the ID of the property to sell: ");
        Integer propertyId = Integer.parseInt(scanner.nextLine().trim());

        Property property = findPropertyById(propertyId);

        if (property == null || property.getState() != StateOfProperty.AVAILABLE) {
            throw new InvalidInputException("Property is not available for sale."); //VER DE MANEJAR OTRA EXCEP
        }

        System.out.print("Enter the buyer's DNI: ");
        String buyerDni = scanner.nextLine().trim();
        Buyer buyer = validateBuyer(buyerDni);

        System.out.print("Enter the sale date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        LocalDate saleDate = LocalDate.parse(date);

        property.setState(StateOfProperty.SOLD);
        properties.modifyElement(property,property);
        JsonUtils.saveList(properties.returnList(), "houses.json", Property.class);

        return new Sale(buyer,property, saleDate);
    }


    private  Property findPropertyById(Integer propertyId) {
        for (Property p : properties.returnList()) {
            if (p.getId().equals(propertyId)) {
                return p;
            }
        }
        return null;
    }

    private Buyer validateBuyer(String buyerDni) throws InvalidInputException{
        for (Buyer b : buyers.returnList()) {
            if (b.getDni().equalsIgnoreCase(buyerDni)) {
                return b;
            }
        }
        throw new InvalidInputException("Owner with DNI " + buyerDni + " not found.");
    }
    private Boolean askToContinue() {
        System.out.print("Do you want to add another sale? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}