package ui.menus.interfaces;


import model.exceptions.InvalidInputException;

import java.time.LocalDate;

public interface DateValidations {
    public Boolean dateValidation(LocalDate fechaInicio, LocalDate fechaFin) throws InvalidInputException;
}
