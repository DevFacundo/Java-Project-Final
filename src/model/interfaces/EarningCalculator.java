package model.interfaces;

import model.properties.Property;

public interface EarningCalculator {
    double calculateTotal(Property p);
    double calculateEarnings(Property p);
}
