package model.rents;

import jdk.vm.ci.meta.Local;
import model.clients.Owner;
import model.clients.Tenant;
import model.properties.Property;

import java.time.LocalDate;


public class Rent {
    private Integer id;
    private static Integer nextId=1;
    private Tenant tenant;
    private Property property;
    private Owner owner;
    private LocalDate startRent;
    private LocalDate endRent;

}
