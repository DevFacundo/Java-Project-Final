package model.clients;

public class Owner extends Client {

    public Owner(){
    }

    public Owner(String name, String surname, String dni, String contactNumber, String email, String adress) {
        super(name, surname, dni, contactNumber, email, adress);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
