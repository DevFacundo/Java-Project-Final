package ui.menus.salesmenu;

import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.sales.Sale;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class SalesMenu {

    Scanner scanner = new Scanner(System.in);

    public void menu() {
        int option = -1;
        do {
            printMenu(); /// Imprime un menú modularizado

            try {
                option = getValidatedOption(); /// Excepción que valida que sea un número

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
        System.out.println("│          SALES MENU           │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD SALE                   │");
        System.out.println("│ 2. MODIFY A SALE              │");
        System.out.println("│ 3. CANCEL A SALE              │");
        System.out.println("│ 4. VIEW ALL SALES             │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("│                               │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

   /* private void addSale(){

        do{
            try{
                GenericClass<Sale> sales = new GenericClass<>();
                sales = JsonUtils.loadList("sales.json",Sale.class);
                Sale newSale =


            }


        }

    }*/
}


