import com.fasterxml.jackson.databind.ObjectMapper;
import model.clients.Buyer;
import model.clients.Client;
import model.clients.Owner;

import model.genericManagement.GenericClass;
import model.genericManagement.JsonClass;
import model.properties.*;
import model.sales.Sale;
import service.PropertyManagementService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //PROPERTIES FILES
        /*
        File houseFile = new File("houses.json");
        File lotFile = new File("lots.json");
        File apartmentFile = new File ("apartment.json");
        File StoreFile = new File ("store.json");

         */
        File propertiesFile = new File ("properties.json");


        //CLIENTS FILES
        File ownerFile = new File("owners.json");
        File buyerFile = new File("buyers.json");
        //SALES FILES
        File saleFile = new File("sales.json");
        // RENTS FILES
        File rentFile = new File ("rents.json");

        Owner owner2 = new Owner("Yago Sosa", "312547698", "yagososa@example.com", "Av. Libertador 1234");
        Owner owner1 = new Owner("Facundo Aguilera", "2235829879", "facuaguilera32@gmail.com", "Mascias 2069");
        Apartment ap1 = new Apartment(owner2, "Bigotes 123", 20D, 125000D, 500D, StateOfProperty.AVAILABLE,3,2,2 ,true, Orientation.FRONT,20D);

        //Buyer buyer = new Buyer("María López", "3456789012", "mlopez@example.com", "Av. del Libertador 1234");
        //Buyer buyer2 = new Buyer("Carlos Pérez", "9876543210", "cperez@example.com", "Calle Ficticia 5678");
       // Buyer buyer3 = new Buyer("Ana Gómez", "1122334455", "agomez@example.com", "Calle Mayor 4321");
        House house1 = new House(owner1, "Ayolas 4542", 25D, 145000D, 600D, StateOfProperty.AVAILABLE, 2, 5, 3, 3, true);
        //Sale sale1 = new Sale(buyer, house1, LocalDate.of(2021, 5, 20));

        //GenericClass<Buyer> buyer1 = new GenericClass<>();
        GenericClass<Property> properties = new GenericClass<>();
        properties.addElement(ap1);
        properties.addElement(house1);
        List <Property> prop = new LinkedList<>();
        prop.add(ap1);
        prop.add(house1);

        //buyer1.addElement(buyer);
        //buyer1.addElement(buyer2);
        //JsonClass.printJson(ap1);
        JsonClass.saveList(prop, "properties2.json");
        //JsonClass.saveList(properties.returnList(), "properties.json");
        //buyer1.showElements();

        //GenericClass<Property> propertyList = new GenericClass<>(JsonClass.loadList("properties.json", Property.class));
        //System.out.println(propertyList.returnList());
        //propertyList.showElements();

        /*


        Buyer b = buyerList.getLastObject();
        Integer lastId = b.getId() + 1;
        buyer3.setId(lastId);
        buyerList.addElement(buyer3);
        //buyerList.addElement(buyer3);
        //buyerList.deleteElement(buyerList.getLastObject());

        JsonClass.saveList(buyerList.returnList(), "buyers.json");

        buyerList.showElements();


 */

        //PropertyManagementService sm = new PropertyManagementService();
        //sm.menu();
    }
}