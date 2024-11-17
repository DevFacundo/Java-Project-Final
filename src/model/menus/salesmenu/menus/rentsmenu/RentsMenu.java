package model.menus.salesmenu.menus.rentsmenu;

import model.exceptions.InvalidInputException;

import java.util.Scanner;

public class RentsMenu {

    Scanner scanner = new Scanner(System.in);

//    /// Simula limpiar la consola
//    private void clearConsole() {
//        for (int i = 0; i < 50; i++) {
//            System.out.println();
//        }
//    }

    /// Metodo del menu
    public void menu() {
        int option = -1;
        do {
//                clearConsole();
            printMenu(); /// Imprime un menu modularizado

            try {
                option = getValidatedOption(); /// Excepcion que valida que sea un numero
//                    clearConsole();

                switch (option) {
                    case 1:
                        System.out.println("Opcion 1");
                        break;
                    case 2:
                        System.out.println("Opcion 2");
                        break;
                    case 3:
                        System.out.println("Opcion 3");
                        break;
                    case 4:
                        System.out.println("Opcion 4");
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
            }
        } while (option != 0);
    }


    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│         RENTS MENU            │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD RENT                   │");
        System.out.println("│ 2. MODIFY A RENT              │");
        System.out.println("│ 3. CANCEL A RENT              │");
        System.out.println("│ 4. VIEW ALL RENTS             │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

    private int getValidatedOption() throws InvalidInputException {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input must be a number. Please try again.");
        }
    }
}
