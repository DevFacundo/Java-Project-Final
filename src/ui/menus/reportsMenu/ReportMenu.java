package ui.menus.reportsMenu;

import model.exceptions.InvalidInputException;
import model.rents.Rent;
import model.sales.Sale;
import service.reportService.ReportService;

import java.util.Scanner;

import static utils.Utils.getValidatedOption;

public class ReportMenu {
    Scanner scanner;
    ReportService rs;
    Rent topRent;
    Sale topSale;

    public ReportMenu() {
        scanner = new Scanner(System.in);
        rs = new ReportService();
        topRent = new Rent();
        topSale = new Sale();
    }
    public void menu() {
        int option = -1;
        do {
            printMenu();
            try {
                option = getValidatedOption();

                switch (option) {
                    case 1:
                        rs.calculateEarnings();
                        break;
                    case 2:
                        System.out.println("THE TOP SALE");
                        topSale = rs.topSale();
                        System.out.println(topSale);
                        break;
                    case 3:
                        System.out.println("THE TOP RENT");
                        topRent = rs.topRent();
                        System.out.println(topRent);
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
        System.out.println("│ 1. CALCULATE EARNINGS         │");
        System.out.println("│ 2. TOP SALE                   │");
        System.out.println("│ 3. TOP RENT                   │");
        System.out.println("│ 0. GO BACK                    │");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Choose an option: ");
    }


}
