package model.properties;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.clients.Owner;
@JsonTypeName("store")
public class Store extends Property{
    private Integer bathRooms;
    private Orientation orientation;
    private Integer floorsQuantity;

    public Store() {
    }


    public Store(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice, StateOfProperty state, Integer bathRooms, Orientation orientation, Integer floorsQuantity) {
        super(owner, adress, area, salesPrice, rentalPrice, state);
        this.bathRooms = bathRooms;
        this.orientation = orientation;
        this.floorsQuantity = floorsQuantity;
    }

    public Integer getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(Integer bathRooms) {
        this.bathRooms = bathRooms;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Integer getFloorsQuantity() {
        return floorsQuantity;
    }

    public void setFloorsQuantity(Integer floorsQuantity) {
        this.floorsQuantity = floorsQuantity;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nbathRooms=" + bathRooms +
                "\norientation=" + orientation +
                "\nfloorsQuantity=" + floorsQuantity ;
    }
}
