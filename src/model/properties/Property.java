package model.properties;

import model.clients.Owner;

public class Property {
    private Integer id;
    private static Integer nextId=1;
    private Owner owner;
    private String adress;
    private Double area;
    private Double SalesPrice;
    private Double RentalPrice;
    private Boolean rented;
    private Boolean sold;

}
