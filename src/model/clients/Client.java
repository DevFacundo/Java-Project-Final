package model.clients;

public class Client {
    private Integer id;
    private static Integer nextId=1;
    private String name;
    private String contactNumber;
    private String email;
    private String adress;

    public Client() {
    }

    public Client(String name, String contactNumber, String email, String adress) {
        id=nextId++;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.adress = adress;
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
    public String toString() {
        return  "id=" + id +
                "\nname='" + name + '\'' +
                "\ncontactNumber=" + contactNumber +
                "\nemail='" + email + '\'' +
                "\nadress='" + adress + '\'';
    }
}
