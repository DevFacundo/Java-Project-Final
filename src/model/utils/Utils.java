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

    public static void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public static void validateInputs(String name, String surname, String contactNumber, String email, String address, String dni) throws InvalidInputException {
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
    }

}
