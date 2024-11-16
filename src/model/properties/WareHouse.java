package model.properties;

import model.clients.Owner;

public class WareHouse extends Property{
    private TypeOfUse typeOfUse;
    private Integer bathRooms;
    private Integer floorsQuantity;

    public WareHouse() {
    }

    public WareHouse(TypeOfUse typeOfUse, Integer bathRooms, Integer floorsQuantity) {
        this.typeOfUse = typeOfUse;
        this.bathRooms = bathRooms;
        this.floorsQuantity = floorsQuantity;
    }

    public WareHouse(Owner owner, String adress, Double salesPrice, Double area, Double rentalPrice, StateOfProperty state, TypeOfUse typeOfUse, Integer bathRooms, Integer floorsQuantity) {
        super(owner, adress, salesPrice, area, rentalPrice, state);
        this.typeOfUse = typeOfUse;
        this.bathRooms = bathRooms;
        this.floorsQuantity = floorsQuantity;
    }

    public TypeOfUse getTypeOfUse() {
        return typeOfUse;
    }

    public void setTypeOfUse(TypeOfUse typeOfUse) {
        this.typeOfUse = typeOfUse;
    }

    public Integer getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(Integer bathRooms) {
        this.bathRooms = bathRooms;
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
                "\ntypeOfUse=" + typeOfUse +
                "\nbathRooms=" + bathRooms +
                "\nfloorsQuantity=" + floorsQuantity;
    }
}
