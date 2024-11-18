package model.properties;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.State;
import model.clients.Owner;
import model.exceptions.InvalidAreaException;
import model.exceptions.InvalidPriceException;

import java.util.Objects;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "propertyType",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = House.class, name = "house"),
        @JsonSubTypes.Type(value = Apartment.class, name = "apartment"),
        @JsonSubTypes.Type(value = Lot.class, name = "lot"),
        @JsonSubTypes.Type(value = WareHouse.class, name = "warehouse"),
        @JsonSubTypes.Type(value = Store.class, name = "store")
})

public abstract class Property {
    private Integer id;
    private static Integer nextId=1;
    private Owner owner;
    private String adress;
    private Double area;
    private Double SalesPrice;
    private Double RentalPrice;
    private State propertyState;

    public Property() {
    }

    public Property(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice) {
        this.owner = owner;
        this.adress = adress;
        this.area = area;
        SalesPrice = salesPrice;
        RentalPrice = rentalPrice;
        this.id=nextId++;
        this.propertyState = State.AVAILABLE;
    }
/*
    public Property(Owner owner, String adress, Double salesPrice, Double area, Double rentalPrice, StateOfProperty state) {
        this.owner = owner;
        this.adress = adress;
        SalesPrice = salesPrice;
        this.area = area;
        RentalPrice = rentalPrice;
        this.state = state;
        this.id=nextId++;
    }
 */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getSalesPrice() {
        return SalesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        if (salesPrice <= 0){throw new InvalidPriceException("the price must be greater than 0");}
        SalesPrice = salesPrice;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        if(area<= 0){throw new InvalidAreaException("the area must be greater than 0");}
        this.area = area;
    }

    public Double getRentalPrice() {
        return RentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        if(rentalPrice <= 0){throw new InvalidPriceException("the price must be greater than 0");}
        RentalPrice = rentalPrice;
    }

    public State getState() {
        return propertyState;
    }

    public void setState(State state) {
        this.propertyState = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(adress, property.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(adress);
    }

    @Override
    public String toString() {
        return String.format(
                "Property Information:\n" +
                        "─────────────────────────────────\n" +
                        "ID Property        : %d\n" +
                        "Owner              : %s " + "%s\n"+
                        "Address            : %s\n" +
                        "Area               : %.2f\n" +
                        "Sales Price        : $%.2f\n" +
                        "Rental Price       : $%.2f\n" +
                        "State of Property  : %s\n" +
                        "─────────────────────────────────",
                id, owner.getName(),owner.getSurname(), adress, area, SalesPrice, RentalPrice, propertyState
        );
    }
}
