package model.rents;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.clients.Tenant;
import model.interfaces.EarningCalculator;
import model.properties.Property;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rent implements EarningCalculator {
    private Integer id;
    private static Integer nextId = 1;
    private Tenant tenant;
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "propertyType"
    )
    private Property property;
    private LocalDate startRent;
    private LocalDate endRent;
    private static final double RENT_COMISSION = 0.05;

    public Rent() {
    }

    public Rent(Tenant tenant, Property property, LocalDate startRent, LocalDate endRent) {
        id = nextId++;
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
        return String.format(
                "\n" +
                        "Rent Information:\n" +
                        "─────────────────────────────────\n" +
                        "ID Rent            : %d\n" +
                        "Tenant             : %s " + "%s\n" +
                        "Start Rental Date  : %s\n" +
                        "End Rental Date    : %s\n" +
                        "─────────────────────────────────\n" +
                        "%s\n",
                 id, tenant.getName(), tenant.getSurname(), startRent, endRent, property
        );
    }

    private long calculateMonths()
    {
        return ChronoUnit.MONTHS.between(startRent,endRent);
    }

    @Override
    public double calculateTotal(Property property) {
        return property.getRentalPrice()* calculateMonths();
    }

    @Override
    public double calculateEarnings(Property property) {
        return calculateTotal(property)* RENT_COMISSION;
    }
}
