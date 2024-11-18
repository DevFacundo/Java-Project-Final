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

    public Integer getFloorsQuantity() {
        return floorsQuantity;
    }

    public void setFloorsQuantity(Integer floorsQuantity) {
        this.floorsQuantity = floorsQuantity;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(Integer bedRooms) {
        this.bedRooms = bedRooms;
    }

    public Integer getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(Integer bathRooms) {
        this.bathRooms = bathRooms;
    }

    public Boolean getPark() {
        return park;
    }

    public void setPark(Boolean park) {
        this.park = park;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n" +
                        "House Information:\n" +
                        "─────────────────────────────────\n" +
                        "Floors Quantity    : %d\n" +
                        "Rooms              : %d\n" +
                        "Bedrooms           : %d\n" +
                        "Bathrooms          : %d\n" +
                        "Park               : %b\n" +
                        "─────────────────────────────────",
                super.toString(), floorsQuantity, rooms, bedRooms, bathRooms, park
        );
    }

}
