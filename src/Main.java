import model.clients.Buyer;
import model.clients.Owner;

import model.clients.Tenant;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.*;
import model.rents.Rent;
import model.sales.Sale;
import ui.menus.mainMenu.MainMenu;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        MainMenu mm = new MainMenu();
        mm.menu();
        //PROPERTIES FILES
        File houseFile = new File("houses.json");
        File lotFile = new File("lots.json");
        File apartmentFile = new File ("apartments.json");
        File storeFile = new File ("stores.json");
        File wareHouseFile = new File ("warehouses.json");

        File propertyFile = new File ("properties.json");

        //CLIENTS FILES
        File ownerFile = new File("owners.json");
        File buyerFile = new File("buyers.json");
        File tenantFile = new File ("tenants.json");

        //SALES FILES
        File saleFile = new File("sales.json");

        // RENTS FILES
        File rentFile = new File ("rents.json");


        //MainMenu mm = new MainMenu();
        //mm.menu();
        //INSTANCIAS PARA PERSISTIR

        Owner owner1 = new Owner("Walter", "White", "12345678", "5051234567", "walter.white@breakingbad.com", "Albuquerque, NM");
        Owner owner2 = new Owner("Homer", "Simpson", "23456789", "5551234567", "homer.simpson@simpsons.com", "742 Evergreen Terrace");
        Owner owner3 = new Owner("Jon", "Snow", "34567890", "5559876543", "jon.snow@got.com", "Winterfell");
        Owner owner4 = new Owner("Daenerys", "Targaryen", "45678901", "5557654321", "daenerys.targaryen@got.com", "Dragonstone");
        Owner owner5 = new Owner("Rick", "Sanchez", "56789012", "5552345678", "rick.sanchez@rickandmorty.com", "The Citadel");
        Owner owner6 = new Owner("Jessica", "Day", "67890123", "5558765432", "jessica.day@newgirl.com", "Los Angeles, CA");
        Owner owner7 = new Owner("Sherlock", "Holmes", "78901234", "5551122334", "sherlock.holmes@sherlockbbc.com", "Baker Street 221B");
        Owner owner8 = new Owner("Alfredo", "Casero", "89012345", "5553344556", "eleven@strangerthings.com", "Hawkins, Indiana");
        Owner owner9 = new Owner("Michael", "Scott", "90123456", "5557788990", "michael.scott@dundermifflin.com", "Scranton, PA");
        Owner owner10 = new Owner("Phoebe", "Buffay", "01234567", "5556667778", "phoebe.buffay@friends.com", "New York, NY");
        Owner owner11 = new Owner("The", "Mandalorian", "12345678", "5554433221", "mandalorian@starwars.com", "Tatooine");
        Owner owner12 = new Owner("Jim", "Halpert", "23456789", "5559871234", "jim.halpert@dundermifflin.com", "Scranton, PA");

        // Buyers con dni hardcodeado y nombre separado
        Buyer buyer1 = new Buyer("Yago", "Sosa", "34567890", "312547698", "yagososa@example.com", "Av. Libertador 1234");
        Buyer buyer2 = new Buyer("Facundo", "Aguilera", "45678901", "2235829879", "facuaguilera32@gmail.com", "Mascias 2069");
        Buyer buyer3 = new Buyer("María", "López", "56789012", "3456789012", "mlopez@example.com", "Av. del Libertador 8594");
        Buyer buyer4 = new Buyer("Carlos", "Pérez", "67890123", "9876543210", "cperez@example.com", "Calle Ficticia 5678");
        Buyer buyer5 = new Buyer("Lucía", "Gómez", "78901234", "1122334455", "lucia.gomez@example.com", "Calle de la Paz 234");
        Buyer buyer6 = new Buyer("Ricardo", "Rodríguez", "89012345", "2233445566", "ricardo.rodriguez@example.com", "Av. San Martín 123");
        Buyer buyer7 = new Buyer("Ana María", "Fernández", "90123456", "3344556677", "ana.fernandez@example.com", "Calle Belgrano 789");
        Buyer buyer8 = new Buyer("Martín", "Sánchez", "01234567", "4455667788", "martin.sanchez@example.com", "Paseo Colón 456");
        Buyer buyer9 = new Buyer("Sofía", "García", "12345678", "5566778899", "sofia.garcia@example.com", "Av. Rivadavia 101");
        Buyer buyer10 = new Buyer("Juan Martin", "López", "23456789", "6677889900", "juan.martin@example.com", "Calle 9 de Julio 321");
        Buyer buyer11 = new Buyer("Esteban", "Díaz", "34567890", "7788990011", "esteban.diaz@example.com", "Calle Rodríguez Peña 543");
        Buyer buyer12 = new Buyer("Cecilia", "Castro", "45678901", "8899001122", "cecilia.castro@example.com", "Av. Cabildo 876");

        // Tenants con dni hardcodeado y nombre separado
        Tenant tenant1 = new Tenant("Susana", "Giménez", "56789012", "01112345678", "susana.gimenez@telefe.com.ar", "Av. Libertador 5000, Buenos Aires");
        Tenant tenant2 = new Tenant("Ricardo", "Darín", "67890123", "01187654321", "ricardo.darin@cine.com.ar", "Av. Corrientes 1500, Buenos Aires");
        Tenant tenant3 = new Tenant("Mirtha", "Legrand", "78901234", "01123456789", "mirtha.legrand@eltrece.com.ar", "Calle Florida 500, Buenos Aires");
        Tenant tenant4 = new Tenant("Lionel", "Messi", "89012345", "03417654321", "leo.messi@barcelona.com", "Rosario, Santa Fe");
        Tenant tenant5 = new Tenant("Mariana", "Fabbiani", "90123456", "01134567890", "mariana.fabbiani@eltrece.com.ar", "Calle Rodríguez Peña 200, Buenos Aires");
        Tenant tenant6 = new Tenant("Ángel", "De Brito", "21234567", "01165432109", "angel.debrito@eltrece.com.ar", "Av. de Mayo 700, Buenos Aires");
        House house1 = new House(owner1, "Ayolas 4542", 25D, 145000D, 600D, StateOfProperty.AVAILABLE, 2, 5, 3, 3, true);
        House house2 = new House(owner2, "Pueyrredón 1023", 30D, 200000D, 800D, StateOfProperty.AVAILABLE, 3, 4, 2, 2, false);
        House house3 = new House(owner3, "Av. Santa Fe 870", 20D, 120000D, 550D, StateOfProperty.AVAILABLE, 2, 3, 1, 1, true);
        House house4 = new House(owner4, "Callejón del Sol 123", 45D, 300000D, 1200D, StateOfProperty.AVAILABLE, 4, 6, 4, 5, true);
        House house5 = new House(owner5, "Calle Viamonte 456", 35D, 250000D, 950D, StateOfProperty.AVAILABLE, 3, 4, 2, 3, false);
        House house6 = new House(owner6, "Av. 9 de Julio 1750", 28D, 180000D, 700D, StateOfProperty.AVAILABLE, 3, 5, 2, 3, true);
        House house7 = new House(owner1, "Calle de los Árboles 789", 25D, 140000D, 650D, StateOfProperty.AVAILABLE, 2, 4, 2, 2, true);

        Apartment apartment1 = new Apartment(owner1, "Av. Libertador 1000", 70D, 150000D, 700D, StateOfProperty.AVAILABLE, 3, 2, 2, true, Orientation.FRONT, 30D);
        Apartment apartment2 = new Apartment(owner2, "Calle Florida 700", 90D, 200000D, 650D, StateOfProperty.AVAILABLE, 4, 3, 3, false, Orientation.BACK, 40D);
        Apartment apartment3 = new Apartment(owner3, "Av. Santa Fe 2000", 55D, 130000D, 350D, StateOfProperty.AVAILABLE, 2, 1, 1, true, Orientation.BACK, 25D);
        Apartment apartment4 = new Apartment(owner4, "Calle Viamonte 500", 80D, 180000D, 900D, StateOfProperty.AVAILABLE, 3, 2, 2, true, Orientation.FRONT, 35D);
        Apartment apartment5 = new Apartment(owner5, "Calle 9 de Julio 1200", 75D, 170000D, 720D, StateOfProperty.AVAILABLE, 3, 2, 2, false, Orientation.FRONT, 32D);
        Apartment apartment6 = new Apartment(owner6, "Av. 9 de Julio 800", 65D, 150000D, 560D, StateOfProperty.AVAILABLE, 2, 2, 1, true, Orientation.FRONT, 27D);

        Lot lot1 = new Lot(owner7, "Ruta 8 Km 32", 1000D, 50000D, null, StateOfProperty.AVAILABLE, true, true, true, true, true);
        Lot lot2 = new Lot(owner8, "Calle Falsa 123", 1500D, 75000D, null, StateOfProperty.AVAILABLE, true, true, false, true, true);
        Lot lot3 = new Lot(owner9, "Av. Rivadavia 3000", 2000D, 100000D, null, StateOfProperty.AVAILABLE, true, false, true, false, true);
        Lot lot4 = new Lot(owner10, "Calle Belgrano 750", 1200D, 60000D, null, StateOfProperty.AVAILABLE, false, true, true, true, false);
        Lot lot5 = new Lot(owner11, "Calle de los Árboles 210", 1800D, 90000D, null, StateOfProperty.AVAILABLE, true, true, true, false, true);
        Lot lot6 = new Lot(owner12, "Av. 9 de Julio 1800", 2500D, 125000D, null, StateOfProperty.AVAILABLE, true, true, true, true, true);

        Store store1 = new Store(owner1, "Calle Florida 500", 120D, 250000D, 1050D, StateOfProperty.AVAILABLE, 2, Orientation.FRONT, 1);
        Store store2 = new Store(owner2, "Av. Corrientes 1500", 150D, 300000D, 1250D, StateOfProperty.AVAILABLE, 3, Orientation.FRONT, 2);
        Store store3 = new Store(owner3, "Calle Viamonte 800", 100D, 200000D, 1300D, StateOfProperty.AVAILABLE, 1, Orientation.FRONT, 1);
        Store store4 = new Store(owner4, "Av. Santa Fe 1800", 180D, 400000D, 980D, StateOfProperty.AVAILABLE, 4, Orientation.BACK, 2);
        Store store5 = new Store(owner5, "Calle 9 de Julio 1200", 110D, 220000D, 1020D, StateOfProperty.AVAILABLE, 2, Orientation.BACK, 1);

        WareHouse warehouse1 = new WareHouse(owner1, "Calle Independencia 800", 300000D, 1500D, 3000D, StateOfProperty.AVAILABLE, TypeOfUse.COMMERCIAL, 3, 2);
        WareHouse warehouse2 = new WareHouse(owner2, "Av. Belgrano 1500", 500000D, 2500D, 6000D, StateOfProperty.AVAILABLE, TypeOfUse.INDUSTRIAL, 5, 3);
        WareHouse warehouse3 = new WareHouse(owner3, "Calle Lavalle 1200", 200000D, 1000D, 2000D, StateOfProperty.AVAILABLE, TypeOfUse.STORAGE, 2, 1);
        WareHouse warehouse4 = new WareHouse(owner4, "Av. Corrientes 2000", 450000D, 1800D, 4500D, StateOfProperty.AVAILABLE, TypeOfUse.COMMERCIAL, 4, 2);
        WareHouse warehouse5 = new WareHouse(owner5, "Calle de los Árboles 50", 600000D, 3000D, 7000D, StateOfProperty.AVAILABLE, TypeOfUse.INDUSTRIAL, 6, 4);


        Rent rent1 = new Rent(tenant1, house1, owner1, LocalDate.of(2023,1,15), LocalDate.of(2024,1,15));
        Rent rent2 = new Rent(tenant2, apartment1, owner2, LocalDate.of(2023, 3, 1), LocalDate.of(2024, 3, 1));
        Rent rent3 = new Rent(tenant3, store1, owner3, LocalDate.of(2023, 6, 1), LocalDate.of(2024, 6, 1));
        Rent rent4 = new Rent(tenant4, warehouse1, owner4, LocalDate.of(2023, 5, 10), LocalDate.of(2024, 5, 10));
        Rent rent5 = new Rent(tenant5, lot1, owner5, LocalDate.of(2023, 7, 20), LocalDate.of(2024, 7, 20));
        Rent rent6 = new Rent(tenant6, house2, owner6, LocalDate.of(2023, 8, 5), LocalDate.of(2024, 8, 5));

        Sale sale1 = new Sale(buyer1, house1, LocalDate.of(2021, 5, 20));
        Sale sale2 = new Sale(buyer2, apartment1, LocalDate.of(2022, 3, 15));
        Sale sale3 = new Sale(buyer3, lot1, LocalDate.of(2023, 7, 10));
        Sale sale4 = new Sale(buyer4, warehouse1, LocalDate.of(2022, 6, 25));
        Sale sale5 = new Sale(buyer5, house2, LocalDate.of(2023, 1, 5));
        Sale sale6 = new Sale(buyer6, store1, LocalDate.of(2023, 9, 1));


        System.out.println(JsonUtils.loadList("owners.json", Owner.class));
        System.out.println(JsonUtils.loadList("buyers.json", Buyer.class));
        System.out.println(JsonUtils.loadList("tenants.json", Tenant.class));
        /*
        GenericClass<Owner> owners = new GenericClass<>();
        owners.addElement(owner1);
        owners.addElement(owner2);
        owners.addElement(owner3);
        owners.addElement(owner4);
        owners.addElement(owner5);
        owners.addElement(owner6);
        owners.addElement(owner7);
        owners.addElement(owner8);
        owners.addElement(owner9);
        owners.addElement(owner10);
        owners.addElement(owner11);
        owners.addElement(owner12);
        JsonUtils.saveList(owners.returnList(), "owners.json", Owner.class);

        GenericClass<Buyer> buyers = new GenericClass<>();
        buyers.addElement(buyer1);
        buyers.addElement(buyer2);
        buyers.addElement(buyer3);
        buyers.addElement(buyer4);
        buyers.addElement(buyer5);
        buyers.addElement(buyer6);
        buyers.addElement(buyer7);
        buyers.addElement(buyer8);
        buyers.addElement(buyer9);
        buyers.addElement(buyer10);
        buyers.addElement(buyer11);
        buyers.addElement(buyer12);
        JsonUtils.saveList(buyers.returnList(), "buyers.json", Buyer.class);

        GenericClass<Tenant> tenants = new GenericClass<>();
        tenants.addElement(tenant1);
        tenants.addElement(tenant2);
        tenants.addElement(tenant3);
        tenants.addElement(tenant4);
        tenants.addElement(tenant5);
        tenants.addElement(tenant6);
        JsonUtils.saveList(tenants.returnList(), "tenants.json", Tenant.class);


         */
        //PRUEBA DE CARGA GENERICA PROPERTIES COMPLETA
        /*
        GenericClass<Property> properties = new GenericClass<>();

        properties.addElement(house1);
        properties.addElement(house2);
        properties.addElement(house3);
        properties.addElement(house4);
        properties.addElement(house5);
        properties.addElement(house6);
        properties.addElement(house7);

        properties.addElement(apartment1);
        properties.addElement(apartment2);
        properties.addElement(apartment3);
        properties.addElement(apartment4);
        properties.addElement(apartment5);
        properties.addElement(apartment6);

        properties.addElement(lot1);
        properties.addElement(lot2);
        properties.addElement(lot3);
        properties.addElement(lot4);
        properties.addElement(lot5);
        properties.addElement(lot6);

        properties.addElement(store1);
        properties.addElement(store2);
        properties.addElement(store3);
        properties.addElement(store4);
        properties.addElement(store5);

        properties.addElement(warehouse1);
        properties.addElement(warehouse2);
        properties.addElement(warehouse3);
        properties.addElement(warehouse4);
        properties.addElement(warehouse5);

        JsonUtils.saveList(properties.returnList(),"properties.json", Property.class);

         */
        // PRUEBAS 1
/*

        properties.addElement(house1);
        properties.addElement(house2);
        properties.addElement(apartment3);
        properties.addElement(lot2);
        properties.addElement(warehouse3);
        properties.addElement(store3);
        JsonUtils.saveList(properties.returnList(),"properties.json", Property.class);


 */

        //GenericClass<Property> houses= new GenericClass<>();
        //houses.addElement(house1);
       // houses.addElement(house2);
       // houses.addElement(house3);
       // houses.addElement(house4);
       // houses.addElement(house5);
        //
        //GenericClass<Rent> rents  = new GenericClass<>();
        //rents.addElement(rent1);
        //rents.addElement(rent2);
        //rents.addElement(rent3);
        //JsonUtils.saveList(rents.returnList(), "rents.json", Rent.class);


        /*



        GenericClass<House> houses= new GenericClass<>();
            houses.addElement(house1);
            houses.addElement(house2);
            houses.addElement(house3);
            houses.addElement(house4);
            houses.addElement(house5);
        JsonClass.saveList(houses.returnList(), "houses.json");


        GenericClass<Apartment> apartments= new GenericClass<>();
            apartments.addElement(apartment1);
            apartments.addElement(apartment2);
            apartments.addElement(apartment3);
            apartments.addElement(apartment4);
            apartments.addElement(apartment5);
        JsonClass.saveList(apartments.returnList(), "apartments.json");

        GenericClass<Lot> lots= new GenericClass<>();
            lots.addElement(lot1);
            lots.addElement(lot2);
            lots.addElement(lot3);
            lots.addElement(lot4);
        JsonClass.saveList(lots.returnList(), "lots.json");

        GenericClass<Store> stores= new GenericClass<>();
            stores.addElement(store1);
            stores.addElement(store2);
            stores.addElement(store3);
        JsonClass.saveList(stores.returnList(), "stores.json");

        GenericClass<WareHouse> warehouses= new GenericClass<>();
            warehouses.addElement(warehouse1);
            warehouses.addElement(warehouse2);
            warehouses.addElement(warehouse3);
        JsonClass.saveList(warehouses.returnList(), "warehouses.json");

        GenericClass<Sale> sales = new GenericClass<>();
            sales.addElement(sale1);
            sales.addElement(sale2);
            sales.addElement(sale3);
        JsonClass.saveList(sales.returnList(), "sales.json");

        GenericClass<Rent> rents  = new GenericClass<>();
            rents.addElement(rent1);
            rents.addElement(rent2);
            rents.addElement(rent3);
        JsonClass.saveList(rents.returnList(), "rents.json");


*/



/*
        //LISTAS PARA PROBAR DESSEREALIZACION

        List <Owner> ownerList = new LinkedList<>();
        ownerList = JsonClass.loadList("owners.json", Owner.class);
        System.out.println(ownerList);
        System.out.println();
        System.out.println();
        List <Buyer> buyerList = new LinkedList<>();
        buyerList = JsonClass.loadList("buyers.json", Buyer.class);
        System.out.println(buyerList);
        System.out.println();
        System.out.println();
        List <Tenant> tenantList = new LinkedList<>();
        tenantList = JsonClass.loadList("tenants.json", Tenant.class);
        System.out.println(tenantList);
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------");

        List <House> houseList = new LinkedList<>();
        houseList = JsonClass.loadList("houses.json", House.class);
        System.out.println(houseList);
        List <Apartment> apartmentList = new LinkedList<>();
        apartmentList = JsonClass.loadList("apartments.json", Apartment.class);
        System.out.println(apartmentList);
        List <Lot> lotList = new LinkedList<>();
        List <Store> storeList = new LinkedList<>();
        List <WareHouse> warehouseList = new LinkedList<>();
*/
/*
        List <Property> jeje = new LinkedList<>();
        jeje.add(house1);
        jeje.add(apartment1);
        jeje.add(lot1);
        jeje.add(store1);
        jeje.add(warehouse1);
        JsonClass.saveList(jeje, "houses.json", Property.class);

 */
        List <Property> jejeprint = new LinkedList<>();
        jejeprint = JsonUtils.loadList("properties.json", Property.class);
        System.out.println(jejeprint);
        //List <House> houseList = new LinkedList<>();
       // houseList = JsonClass.loadList("houses.json", House.class);
        //System.out.println(houseList);
  //==================================================================================parte de rentas tiempo de ejecucion
       // List <Sale> saleList = new LinkedList<>();
        //List <Rent> rentList = new LinkedList<>();
        //System.out.println("=================");
       //rentList = JsonUtils.loadList("rents.json", Rent.class);
       //System.out.println(rentList);
       //JsonClass.saveList(properties.returnList(), "properties.json");

        //GenericClass<Property> propertyList = new GenericClass<>(JsonClass.loadList("properties.json", Property.class));
        //System.out.println(propertyList.returnList());
        //propertyList.showElements();

        /*




        Buyer b = buyerList.getLastObject();
        Integer lastId = b.getId() + 1;
        buyer3.setId(lastId);
        buyerList.addElement(buyer3);
        //buyerList.addElement(buyer3);
        //buyerList.deleteElement(buyerList.getLastObject());

        JsonClass.saveList(buyerList.returnList(), "buyers.json");

        buyerList.showElements();


 */

        //PropertyManagementService sm = new PropertyManagementService();
        //sm.menu();
    }
}