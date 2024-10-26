package model.clients;

public class Tenant extends Client{
    public Tenant(){

    }
    public Tenant(String name, String contactNumber, String email, String adress) {
        super(name, contactNumber, email, adress);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
