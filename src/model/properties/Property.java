package model.properties;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.clients.Owner;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "propertyType"
)
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
    private StateOfProperty state;

    public Property() {
    }

    public Property(Owner owner, String adress, Double salesPrice, Double area, Double rentalPrice, StateOfProperty state) {
        this.owner = owner;
        this.adress = adress;
        SalesPrice = salesPrice;
        this.area = area;
        RentalPrice = rentalPrice;
        this.state = state;
        this.id=nextId++;
    }

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
        SalesPrice = salesPrice;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getRentalPrice() {
        return RentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        RentalPrice = rentalPrice;
    }

    public StateOfProperty getState() {
        return state;
    }

    public void setState(StateOfProperty state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Property: " +
                "\nid prop=" + id +
                "\nowner=" + owner.getName() +
                "\nadress='" + adress + '\'' +
                "\narea=" + area +
                "\nSalesPrice=" + SalesPrice +
                "\nRentalPrice=" + RentalPrice +
                "\nStateOfProperty=" + state;
    }
}
