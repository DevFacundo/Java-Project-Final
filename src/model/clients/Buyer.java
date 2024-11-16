package model.clients;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;


//@JsonTypeName("buyer")
public class Buyer extends Client{
    public Buyer() {}

    public Buyer(String name, String contactNumber, String email, String adress) {
        super(name, contactNumber, email, adress);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

