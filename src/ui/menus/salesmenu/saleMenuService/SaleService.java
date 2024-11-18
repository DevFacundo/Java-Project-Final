package ui.menus.salesmenu.saleMenuService;

import model.State;
import model.clients.Buyer;
import model.clients.Owner;
import model.clients.Tenant;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.House;
import model.properties.Property;
import model.rents.Rent;
import model.sales.Sale;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SaleService {
    private GenericClass<Property> properties;
    private GenericClass<Sale> sales;
    private GenericClass<Buyer>buyers;
    private GenericClass<Owner> owners;
    private Scanner scanner= new Scanner(System.in);

    public SaleService() {
        sales = new GenericClass<>(JsonUtils.loadList("sales.json", Sale.class));
        buyers = new GenericClass<>(JsonUtils.loadList("buyers.json", Buyer.class));
        properties =  new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));
        owners = new GenericClass<>(JsonUtils.loadList("owners.json", Owner.class));
        scanner = new Scanner(System.in);
    }

    public void addSale() {
        Boolean continueAdding = true;
        do {
            try {
                Sale newSale = createSale();

                System.out.println("Sale created successfully!");
                System.out.println(newSale);

                if (!sales.isEmpty()) {
                    Sale s = sales.getLastObject();
                    Integer lastId = s.getId() + 1;
                    newSale.setId(lastId);
                }

                sales.addElement(newSale);

                JsonUtils.saveList(sales.returnList(), "sales.json", Sale.class);

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

        if (property == null || property.getState() != State.AVAILABLE) {
            throw new InvalidInputException("Property is not available for sale."); //VER DE MANEJAR OTRA EXCEP
        }

        System.out.print("Enter the buyer's DNI: ");
        String buyerDni = scanner.nextLine().trim();
        Buyer buyer = validateBuyer(buyerDni);

        System.out.print("Enter the sale date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        LocalDate saleDate = LocalDate.parse(date);

                                    //SETTING THE CLIENTS STATE
        //SETTING THE BUYER STATE
        buyer.setClientState(State.SOLD);
        buyers.modifyElement(buyer, buyer);
        JsonUtils.saveList(buyers.returnList(), "buyers.json", Buyer.class);
        //SETTING THE OWNER STATE
        property.getOwner().setClientState(State.SOLD);
        owners.modifyElement(property.getOwner(),property.getOwner());
        JsonUtils.saveList(owners.returnList(),"owners.json",Owner.class);

        //SETTING THE PROPERTY STATE
        property.setState(State.SOLD);
        properties.modifyElement(property,property);
        JsonUtils.saveList(properties.returnList(), "properties.json", Property.class);

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
        throw new InvalidInputException("Buyer with DNI " + buyerDni + " not found.");
    }
    private Boolean askToContinue() {
        System.out.print("Do you want to add another sale? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}