package model.properties;

import model.clients.Owner;

public class House extends Property{
    private Integer floorsQuantity;
    private Integer rooms;
    private Integer bedRooms;
    private Integer bathRooms;
    private Boolean park;

    public House() {
    }

    public House(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice, Boolean rented, Boolean sold, Integer floorsQuantity, Integer rooms, Integer bedRooms, Integer bathRooms, Boolean park) {
        super(owner, adress, area, salesPrice, rentalPrice, rented, sold);
        this.floorsQuantity = floorsQuantity;
        this.rooms = rooms;
        this.bedRooms = bedRooms;
        this.bathRooms = bathRooms;
        this.park = park;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nfloorsQuantity=" + floorsQuantity +
                "\nrooms=" + rooms +
                "\nbedRooms=" + bedRooms +
                "\nbathRooms=" + bathRooms +
                "\npark=" + park +
                '}';
    }
}
