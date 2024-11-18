package ui.menus.propertymenu.propertyMenuService;

import model.clients.Owner;
import model.exceptions.DuplicateElementException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.properties.House;

import java.util.Scanner;

public class HousesService {
    Scanner scanner = new Scanner(System.in);
    GenericClass<House> houses;
    GenericClass<Owner> owners;


    private void addHouse() {
        Boolean continueAdding = true;
        do {
            try {
                 houses = new GenericClass<>(JsonUtils.loadList("houses.json", House.class));
                 owners = new GenericClass<>(JsonUtils.loadList("owner.json", Owner.class));
                House newHouse = createHouse(scanner, owners);

                System.out.println("Buyer added successfully:");
                System.out.println(newHouse);
                if (!houses.isEmpty()) {
                    House h = houses.getLastObject();
                    Integer lastId = h.getId() + 1;
                    newHouse.setId(lastId);
                }
                houses.addElement(newHouse);
                JsonUtils.saveList(houses.returnList(), "buyers.json", House.class);
            } catch (InvalidInputException e) {
                System.out.println("Error adding house: " + e.getMessage());
            } catch (DuplicateElementException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
            continueAdding = askToContinue();
        } while (continueAdding);
    }

    public static House createHouse(Scanner scanner,GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = new Owner();
        System.out.print("Enter the owner's DNI: ");
        String ownerDni = scanner.nextLine().trim();
        owner = validateOwner(ownerDni, ownerList);

        System.out.print("Enter house address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter area: ");
        Double area = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter sales Price: ");
        Double sp = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Rental Price: ");
        Double rp = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Enter floors quantity");
        Integer floorsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter rooms quantity");
        Integer roomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter bedrooms quantity");
        Integer bedroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter bathrooms quantity");
        Integer bathroomsQuantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("The house have park? (Y/N)");


      //  validateHouseInputs(, address, area, sp, rp);

        return new House();//(owner,address,area,sp,rp,floorsQuantity,roomsQuantity,bedroomsQuantity,bathroomsQuantity);
    }

    public static Owner validateOwner(String ownerDni,GenericClass<Owner> ownerList) throws InvalidInputException {
        Owner owner = null;
        for (Owner ow : ownerList.returnList()) {
            if (ow.getDni().equalsIgnoreCase(ownerDni)) {
                owner = ow;
                break;
            }
        }
        if (owner == null) {
            throw new InvalidInputException("Owner with DNI " + ownerDni + " not found.");
        }
        else {return owner;}
    }

  /*  public static void validateHouseInputs( String address, Double area, Double sp, Double rp) throws InvalidInputException {
        if (name.isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }

        if (surname.isEmpty()) {
            throw new InvalidInputException("Surname cannot be empty.");
        }

        if (dni.isEmpty() || !dni.matches("\\d{7,8}")) {
            throw new InvalidInputException("DNI must be between 7 and 8 digits.");
        }

        if (!contactNumber.matches("\\d+")) {
            throw new InvalidInputException("Contact number must contain only digits.");
        }

        if (!email.contains("@")) {
            throw new InvalidInputException("Invalid email format.");
        }

        if (address.isEmpty()) {
            throw new InvalidInputException("Address cannot be empty.");
        }
    }*/

    private Boolean askToContinue() {
        System.out.print("Do you want to add another buyer? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

}
