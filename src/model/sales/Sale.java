package model.sales;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.clients.Buyer;
import model.interfaces.EarningCalculator;
import model.properties.Property;

import java.time.LocalDate;
import java.util.Objects;

public class Sale implements EarningCalculator {
    private Integer id;
    private static  Integer nextId=1;
    private Buyer buyer;
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "propertyType"
    )
    private Property property;
    private LocalDate dateOfSale;
    private static final double SALE_COMISSION = 0.05;


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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(buyer, sale.buyer) && Objects.equals(property, sale.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer, property);
    }

    @Override
    public String toString() {
        return String.format(
                "\n" +

                        "Sale Information:\n" +
                        "─────────────────────────────────\n" +
                        "ID Sale            : %d\n" +
                        "Buyer              : %s %s\n" +
                        "Property Address   : %s\n" +
                        "Date of Sale       : %s\n" +
                        "─────────────────────────────────\n",
                id, buyer.getName(), buyer.getSurname(), property.getAdress(), dateOfSale
        );
    }

    @Override
    public double calculateTotal(Property property) {
        return property.getSalesPrice();
    }

    @Override
    public double calculateEarnings(Property property) {
        return property.getSalesPrice()* SALE_COMISSION;
    }
}
