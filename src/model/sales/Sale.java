package model.sales;

import model.clients.Buyer;
import model.clients.Owner;
import model.properties.Property;

import java.time.LocalDate;

public class Sale {
    private Integer id;
    private static  Integer nextId=1;
    private Buyer buyer;
    private Owner owner;
    private Property property;
    private LocalDate dateOfSale;

}
