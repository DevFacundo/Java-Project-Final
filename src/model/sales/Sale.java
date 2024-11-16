package model.sales;

import model.clients.Buyer;
import model.clients.Owner;
import model.interfaces.Identifiable;
import model.properties.Property;

import java.time.LocalDate;

public class Sale implements Identifiable {
    private Integer id;
    private static  Integer nextId=1;
    private Buyer buyer;
    private Property property;
    private LocalDate dateOfSale;

    public Sale() {
    }

    public Sale(Buyer buyer, Property property, LocalDate dateOfSale) {
        id=nextId++;
        this.buyer = buyer;
        this.property = property;
        this.dateOfSale = dateOfSale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    @Override
    public String toString() {
        return  "\nid=" + id +
                "\nbuyer=" + buyer.getName() +
                "\nproperty adress=" + property.getAdress()+
                "\ndateOfSale=" + dateOfSale;
    }
}
