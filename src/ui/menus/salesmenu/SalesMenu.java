package ui.menus.salesmenu;

import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;
import model.genericManagement.GenericClass;
import model.genericManagement.JsonUtils;
import model.sales.Sale;
import ui.menus.salesmenu.saleMenuService.SaleService;

import java.util.Scanner;

import static model.utils.Utils.getValidatedOption;

public class SalesMenu {

    Scanner scanner;
    SaleService sl;

    public SalesMenu() {
        scanner = new Scanner(System.in);
        sl = new SaleService();
    }

    public void menu() {
        int option = -1;
        do {
            printMenu();

            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        sl.addSale();
                        break;
                    case 2:
                        sl.deleteSale();
                        break;
                    case 3:
                        sl.seeAllSales();
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
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
                System.out.println(e.getMessage());
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│          SALES MENU           │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ 1. ADD SALE                   │");
        System.out.println("│ 2. CANCEL A SALE              │");
        System.out.println("│ 3. VIEW ALL SALES             │");
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


