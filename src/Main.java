import com.fasterxml.jackson.databind.ObjectMapper;
import model.clients.Buyer;
import model.clients.Client;
import model.clients.Owner;

import model.genericManagement.GenericClass;
import model.genericManagement.JsonClass;
import model.properties.House;
import model.properties.StateOfProperty;
import model.sales.Sale;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        File fileHouse = new File("houses.json");
        File fileLot = new File("lots.json");
        File fileOwner = new File("owners.json");
        File fileSale = new File("sales.json");
        File fileBuyer = new File("buyers.json");
        File fileClients = new File("clients.json");

        //Owner owner2 = new Owner("Yago Sosa", "312547698", "yagososa@example.com", "Av. Libertador 1234");
        //Owner owner1 = new Owner("Facundo Aguilera", "2235829879", "facuaguilera32@gmail.com", "Mascias 2069");
        //Buyer buyer = new Buyer("María López", "3456789012", "mlopez@example.com", "Av. del Libertador 1234");
        //Buyer buyer2 = new Buyer("Carlos Pérez", "9876543210", "cperez@example.com", "Calle Ficticia 5678");
        Buyer buyer3 = new Buyer("Ana Gómez", "1122334455", "agomez@example.com", "Calle Mayor 4321");
        //House house1 = new House(owner1, "Ayolas 4542", 25D, 120D, 60D, StateOfProperty.AVAILABLE, 2, 5, 3, 3, true);
        //Sale sale1 = new Sale(buyer, house1, LocalDate.of(2021, 5, 20));

        //GenericClass<Buyer> buyer1 = new GenericClass<>();
        //buyer1.addElement(buyer);
        //buyer1.addElement(buyer2);
        //JsonClass.saveList(buyer1.returnList(), "buyers.json");
        //buyer1.showElements();

        GenericClass<Buyer> buyerList = new GenericClass<>(JsonClass.loadList("buyers.json", Buyer.class));
        Buyer b = buyerList.getLastObject();
        Integer lastId = b.getId() + 1;
        buyer3.setId(lastId);
        buyerList.addElement(buyer3);
        //buyerList.addElement(buyer3);
        //buyerList.deleteElement(buyerList.getLastObject());

        JsonClass.saveList(buyerList.returnList(), "buyers.json");

        buyerList.showElements();


    }
}