package model.clients;

public class Owner extends Client {

    public Owner()
    {

    }
    public Owner(String name, String contactNumber, String email, String adress) {
        super(name, contactNumber, email, adress);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
