package model.properties;

import model.clients.Owner;

public class Property {
    private Integer id;
    private static Integer nextId=1;
    private Owner owner;
    private String adress;
    private Double area;
    private Double SalesPrice;
    private Double RentalPrice;
    private Boolean rented;
    private Boolean sold;

    public Property() {
    }

    public Property(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice, Boolean rented, Boolean sold) {
        id=nextId++;
        this.owner = owner;
        this.adress = adress;
        this.area = area;
        SalesPrice = salesPrice;
        RentalPrice = rentalPrice;
        this.rented = rented;
        this.sold = sold;
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

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Property: " +
                "\nid=" + id +
                "\nowner=" + owner.getName() +
                "\nadress='" + adress + '\'' +
                "\narea=" + area +
                "\nSalesPrice=" + SalesPrice +
                "\nRentalPrice=" + RentalPrice +
                "\nrented=" + rented +
                "\nsold=" + sold;
    }
}
