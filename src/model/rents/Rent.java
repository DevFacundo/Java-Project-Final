package model.rents;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.clients.Owner;
import model.clients.Tenant;
import model.properties.Property;

import java.time.LocalDate;

public class Rent {
    private Integer id;
    private static Integer nextId=1;
    private Tenant tenant;
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "propertyType"
    )
    private Property property;
    private LocalDate startRent;
    private LocalDate endRent;

    public Rent() {
    }

    public Rent(Tenant tenant, Property property, LocalDate startRent, LocalDate endRent) {
        id=nextId++;
        this.tenant = tenant;
        this.property = property;
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
                "\nowner=" + property.getOwner().getName()+" "+property.getOwner().getSurname()+
                "\nstartRent=" + startRent +
                "\nendRent=" + endRent;
    }
}
