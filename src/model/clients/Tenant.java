package model.clients;

public class Tenant extends Client{
    public Tenant(){
    }

    public Tenant(String name, String surname, String dni, String contactNumber, String email, String adress) {
        super(name, surname, dni, contactNumber, email, adress);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
