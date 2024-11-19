package model.properties;

import model.clients.Owner;

public class Store extends Property{
    private Integer bathRooms;
    private Orientation orientation;
    private Integer floorsQuantity;

    public Store() {
    }

    public Store(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice, Integer bathRooms, Orientation orientation, Integer floorsQuantity) {
        super(owner, adress, area, salesPrice, rentalPrice);
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
        return String.format(
                "%s\n" +
                        "Store Information:\n" +
                        "─────────────────────────────────\n" +
                        "Bathrooms          : %d\n" +
                        "Orientation        : %s\n" +
                        "Floors Quantity    : %d\n" +
                        "─────────────────────────────────",
                super.toString(), bathRooms, orientation, floorsQuantity
        );
    }

}
