package model.clients;


//@JsonTypeName("buyer")
public class Buyer extends Client{
    public Buyer() {}

    public Buyer(String name, String surname, String dni, String contactNumber, String email, String adress) {
        super(name, surname, dni, contactNumber, email, adress);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

