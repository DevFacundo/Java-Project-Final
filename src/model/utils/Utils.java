package model.utils;

import model.clients.Client;
import model.clients.Owner;
import model.exceptions.ElementNotFoundException;
import model.exceptions.InvalidInputException;

import java.util.List;
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

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidInputException("Name can only contain letters and spaces.");
        }

        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidInputException("Surname cannot be empty.");
        }
        if (!surname.matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidInputException("Surname can only contain letters and spaces.");
        }

        if (dni == null || dni.trim().isEmpty() || !dni.matches("\\d{7,8}")) {
            throw new InvalidInputException("DNI must be between 7 and 8 digits.");
        }

        if (contactNumber == null || !contactNumber.matches("\\d{10}")) {
            throw new InvalidInputException("Contact number must contain exactly 10 digits.");
        }

        if (email == null || !email.contains("@")) {
            throw new InvalidInputException("Invalid email format.");
        }

        if (address == null || address.trim().isEmpty()) {
            throw new InvalidInputException("Address cannot be empty.");
        }
        if (!address.matches("^(?!\\s*$)[a-zA-Z0-9\\s]+$")) {
            throw new InvalidInputException("Address can only contain letters, numbers, and spaces.");
        }
    }


    public static void validateName(String name) throws InvalidInputException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }

        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidInputException("Name can only contain letters and spaces.");
        }
    }

    public static void validateSurname(String surname) throws InvalidInputException {

        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidInputException("Surname cannot be empty.");
        }

        if (!surname.matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidInputException("Surname can only contain letters and spaces.");
        }
    }

    public static void validateDNI(String dni) throws InvalidInputException {
        if (dni.isEmpty() || !dni.matches("\\d{7,8}")) {
            throw new InvalidInputException("DNI must be between 7 and 8 digits.");
        }
    }

    public static void validateContactNumber(String contactNumber) throws InvalidInputException {
        if (!contactNumber.matches("\\d{10}")) {
            throw new InvalidInputException("Contact number must contain only digits and 10 numbers.");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if (!email.contains("@")) {
            throw new InvalidInputException("Invalid email format.");
        }
    }

    public static void validateAddress(String address) throws InvalidInputException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidInputException("Address cannot be empty.");
        }

        if (!address.matches("^(?!\\s*$)[a-zA-Z0-9\\s]+$")) {
            throw new InvalidInputException("Address can only contain letters, numbers, and spaces.");
        }
    }

    public static void validateArea(Double area) throws InvalidInputException {
        if (area <= 0) {
            throw new InvalidInputException("Area must be greater than zero.");
        }
    }

    public static void validatePrice(Double price) throws InvalidInputException {
        if (price <= 0) {
            throw new InvalidInputException("Price must be greater than zero.");
        }
    }

    public static void validateQuantity(Integer quantity) throws InvalidInputException {
        if (quantity <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero.");
        }
    }

    public static void validateBathrooms(int bathrooms) throws InvalidInputException {
        if (bathrooms < 0) {
            throw new InvalidInputException("Bathrooms quantity must be a positive number.");
        }
    }

    public static void validateFloors(int floors) throws InvalidInputException {
        if (floors < 0) {
            throw new InvalidInputException("Floors quantity must be a positive number.");
        }
    }

}
