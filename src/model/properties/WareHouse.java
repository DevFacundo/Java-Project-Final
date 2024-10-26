package model.properties;

public class WareHouse extends Property{
    private TypeOfUse typeOfUse;
    private Integer bathRooms;
    private Integer floorsQuantity;

    public WareHouse() {
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
