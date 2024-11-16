import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.clients.Buyer;
import model.clients.Owner;
import model.genericManagement.GenericManagement;
import model.properties.House;
import model.properties.Property;
import model.properties.StateOfProperty;
import model.sales.Sale;

import javax.net.ssl.HostnameVerifier;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        File fileHouse = new File ("houses.json");
        File fileLot = new File ("lots.json");
        File fileOwner = new File ("owners.json");
        File fileSale = new File ("sales.json");
        File fileBuyer = new File ("buyers.json");

        Owner owner1 = new Owner("Facundo Aguilera","2235829879","facuaguilera32@gmail.com","Mascias 2069");
        Buyer buyer1 = new Buyer("María López", "3456789012", "mlopez@example.com", "Av. del Libertador 1234");
        House house1 = new House(owner1, "Ayolas 4542", 25D, 120D, 60D, StateOfProperty.AVAILABLE, 2,5,3,3,true);
        Sale sale1 = new Sale(buyer1, house1, LocalDate.of(2021, 5, 20));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(fileSale, sale1);

        Sale sale2 =mapper.readValue(fileSale, Sale.class);

        GenericManagement<House> genericManagement = new GenericManagement<>("houses.json");

        System.out.println(sale2);
    }
}