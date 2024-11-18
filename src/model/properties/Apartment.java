package model.properties;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.clients.Owner;

@JsonTypeName("apartment")
public class Apartment extends Property{
    private Integer rooms;   // has it a rooms ? how many? or null
    private Integer bathRooms;   // has it a bathrooms ? how many? or null
    private Integer bedRooms;  // has it a bedrooms ? how many? or null
    private Boolean furnished; // has it a furnitures ?
    private Orientation orientation;
    private Double maintenanceFees;

    public Apartment(){}

    public Apartment(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice,StateOfProperty state, Integer rooms, Integer bathRooms, Integer bedRooms, Boolean furnished, Orientation orientation, Double maintenanceFees) {
        super(owner, adress, area, salesPrice, rentalPrice, state);
        this.rooms = rooms;
        this.bathRooms = bathRooms;
        this.bedRooms = bedRooms;
        this.furnished = furnished;
        this.orientation = orientation;
        this.maintenanceFees = maintenanceFees;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
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

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Integer getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(Integer bedRooms) {
        this.bedRooms = bedRooms;
    }

    public Double getMaintenanceFees() {
        return maintenanceFees;
    }

    public void setMaintenanceFees(Double maintenanceFees) {
        this.maintenanceFees = maintenanceFees;
    }

    @Override
    public String toString() {
        return super.toString()+" Apartment{" +
                "rooms=" + rooms +
                ", bathRooms=" + bathRooms +
                ", bedRooms=" + bedRooms +
                ", furnished=" + furnished +
                ", orientation=" + orientation +
                ", maintenanceFees=" + maintenanceFees +
                '}';
    }


    /// Hola
}
