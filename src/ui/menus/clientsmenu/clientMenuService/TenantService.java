package ui.menus.clientsmenu.clientMenuService;

import model.clients.Tenant;
import model.exceptions.InvalidInputException;

import java.util.Scanner;

import static model.utils.Utils.validateInputs;

public class TenantService {

    public static Tenant createTenant(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine().trim();

        System.out.print("Enter DNI: ");
        String dni = scanner.nextLine().trim();

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();

        validateInputs(name, surname, contactNumber, email, address, dni);

        return new Tenant(name, surname, dni, contactNumber, email, address);
    }
}