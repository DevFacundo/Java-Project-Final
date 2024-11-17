package model.utils;

import model.exceptions.InvalidInputException;
import java.util.Scanner;

public class Utils {

    public static int getValidatedOption() throws InvalidInputException{
        Scanner sc = new Scanner(System.in);
        try{
            return Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            throw new InvalidInputException("Input must be a number. Please try again.");
        }

    }
}
