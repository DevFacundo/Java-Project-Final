package model.rents;


import model.clients.Owner;
import model.clients.Tenant;
import model.interfaces.Identifiable;
import model.properties.Property;

import java.time.LocalDate;


public class Rent implements Identifiable {
    private Integer id;
    private static Integer nextId=1;
    private Tenant tenant;
    private Property property;
    private Owner owner;
    private LocalDate startRent;
    private LocalDate endRent;

    public Rent() {
    }

    public Rent(Tenant tenant, Property property, Owner owner, LocalDate startRent, LocalDate endRent) {
        id=nextId++;
        this.tenant = tenant;
        this.property = property;
        this.owner = owner;
        this.startRent = startRent;
        this.endRent = endRent;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getStartRent() {
        return startRent;
    }

    public void setStartRent(LocalDate startRent) {
        this.startRent = startRent;
    }

    public LocalDate getEndRent() {
        return endRent;
    }

    public void setEndRent(LocalDate endRent) {
        this.endRent = endRent;
    }

    @Override
    public String toString() {
        return
                "id rent=" + id +
                "\ntenant=" + tenant.getName()+
                "\nproperty adress=" + property.getAdress() +
                "\nowner=" + owner.getName()+
                "\nstartRent=" + startRent +
                "\nendRent=" + endRent;
    }
}
