package model.properties;

import model.clients.Owner;

public class Lot extends Property{
    private Boolean electricity;
    private Boolean water;
    private Boolean sewer;
    private Boolean asphalt;
    private Boolean gas;

    public Lot() {
    }

    public Lot(Owner owner, String adress, Double area, Double salesPrice, Double rentalPrice, StateOfProperty state, Boolean electricity, Boolean water, Boolean sewer, Boolean asphalt, Boolean gas) {
        super(owner, adress, area, salesPrice, rentalPrice, state);
        this.electricity = electricity;
        this.water = water;
        this.sewer = sewer;
        this.asphalt = asphalt;
        this.gas = gas;
    }

    public Boolean getElectricity() {
        return electricity;
    }

    public void setElectricity(Boolean electricity) {
        this.electricity = electricity;
    }

    public Boolean getWater() {
        return water;
    }

    public void setWater(Boolean water) {
        this.water = water;
    }

    public Boolean getSewer() {
        return sewer;
    }

    public void setSewer(Boolean sewer) {
        this.sewer = sewer;
    }

    public Boolean getAsphalt() {
        return asphalt;
    }

    public void setAsphalt(Boolean asphalt) {
        this.asphalt = asphalt;
    }

    public Boolean getGas() {
        return gas;
    }

    public void setGas(Boolean gas) {
        this.gas = gas;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n" +
                        "Lot Information:\n" +
                        "─────────────────────────────────\n" +
                        "Electricity        : %b\n" +
                        "Water              : %b\n" +
                        "Sewer              : %b\n" +
                        "Asphalt            : %b\n" +
                        "Gas                : %b\n" +
                        "─────────────────────────────────",
                super.toString(), electricity, water, sewer, asphalt, gas
        );
    }
}
