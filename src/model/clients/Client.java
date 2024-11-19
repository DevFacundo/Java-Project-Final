package model.clients;

import model.enums.State;

import java.util.Objects;

/*
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
        //property = "clientType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Buyer.class, name = "buyer"),
        @JsonSubTypes.Type(value = Owner.class, name = "owner"),
        @JsonSubTypes.Type(value = Tenant.class, name = "tenant")
})


 */
// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Client  {

    private Integer id;
    private static Integer nextId=1;
    private String name;
    private String surname;
    private String dni;
    private String contactNumber;
    private String email;
    private String adress;
    private State clientState;

    public Client() {
    }

    public Client(String name, String surname, String dni, String contactNumber, String email, String adress) {
        this.id = nextId++;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.contactNumber = contactNumber;
        this.email = email;
        this.adress = adress;
        this.clientState = State.AVAILABLE;
    }

    public State getClientState() {
        return clientState;
    }

    public void setClientState(State clientState) {
        this.clientState = clientState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(dni, client.dni);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return String.format(
                "\nClient Information:\n" +
                        "─────────────────────────────────\n" +
                        "ID            : %d\n" +
                        "Name          : %s\n" +
                        "Surname       : %s\n" +
                        "DNI           : %s\n" +
                        "Contact Number: %s\n" +
                        "Email         : %s\n" +
                        "Address       : %s\n" +
                        "Client State  : %s\n" +
                        "─────────────────────────────────",
                id, name, surname, dni, contactNumber, email, adress, clientState
        );
    }
}
