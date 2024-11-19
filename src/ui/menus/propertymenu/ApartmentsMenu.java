package ui.menus.propertymenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import service.propertyService.ApartmentsService;

import java.util.Scanner;

import static utils.Utils.getValidatedOption;

public class ApartmentsMenu {
    Scanner scanner ;
    ApartmentsService as ;

    public ApartmentsMenu() {
        scanner = new Scanner(System.in);
        as= new ApartmentsService();
    }

    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        as.addApartament();
                        break;
                    case 2:
                        as.modifyApartment();
                        break;
                    case 3:
                        as.deleteProperty();
                        break;
                    case 4:
                        as.seeAllApartments();
                        break;
                    case 0:
                        System.out.println("Returning to the previous menu...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Press Enter to try again...");
                scanner.nextLine();
            } catch (ElementNotFoundException e) {
                System.out.println("Error: "+e.getMessage());;
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│       APARTMENTS MENU         │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD APARTMENT              │");
        System.out.println("│ 2. MODIFY APARTMENT           │");
        System.out.println("│ 3. CANCEL APARTMENT           │");
        System.out.println("│ 4. VIEW APARTMENTS            │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

}
