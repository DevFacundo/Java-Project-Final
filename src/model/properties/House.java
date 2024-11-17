package model.properties;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.clients.Owner;
@JsonTypeName("house")

public class House extends Property{
    private Integer floorsQuantity;
    private Integer rooms;
    private Integer bedRooms;
    private Integer bathRooms;
    private Boolean park;

    public House() {
    }

    public House(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice,StateOfProperty state, Integer floorsQuantity, Integer rooms, Integer bedRooms, Integer bathRooms, Boolean park) {
        super(owner, adress, area, salesPrice, rentalPrice,state);
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
