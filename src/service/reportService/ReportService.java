package service.reportService;

import utils.genericManagement.GenericClass;
import utils.genericManagement.JsonUtils;
import model.properties.Property;
import model.rents.Rent;
import model.sales.Sale;

public class ReportService {
    private GenericClass<Rent> rents;
    private GenericClass<Sale> sales;
    private GenericClass<Property> properties;

    public ReportService() {
        rents = new GenericClass<>(JsonUtils.loadList("rents.json", Rent.class));
        sales = new GenericClass<>(JsonUtils.loadList("sales.json", Sale.class));
        properties = new GenericClass<Property>(JsonUtils.loadList("properties.json", Property.class));
    }

    public void calculateEarnings() {

        try {
            rents = new GenericClass<>(JsonUtils.loadList("rents.json", Rent.class));
            sales = new GenericClass<>(JsonUtils.loadList("sales.json", Sale.class));
            properties = new GenericClass<Property>(JsonUtils.loadList("properties.json", Property.class));

            Double totalSaleEarnings = 0.0;
            Double totalRentEarnings = 0.0;
            Double totalEarnings;

            for (Rent r : rents.returnList()) {
                Property p = findPropertyById(r.getProperty().getId());
                if (p != null) {
                    totalRentEarnings += r.calculateEarnings(p);
                }

            }

            for (Sale s : sales.returnList()) {
                Property p = findPropertyById(s.getProperty().getId());
                if (p != null) {
                    totalSaleEarnings += s.calculateEarnings(p);
                }
            }

            totalEarnings = totalSaleEarnings + totalRentEarnings;
            String reports = String.format(
                    "\nEarning Reports:\n" +
                            "─────────────────────────────────\n" +
                            "SALES EARNINGS : %.2f\n" +
                            "RENTS EARNINGS : %.2f\n" +
                            "TOTAL EARNINGS : %.2f\n" +
                            "─────────────────────────────────",
                    totalSaleEarnings, totalRentEarnings, totalEarnings);

            System.out.println(reports);

        } catch (Exception e) {
            System.out.println("Error calculating earnings: " + e.getMessage());
        }

    }

    public Rent topRent() {
        try {
            Rent topRent = new Rent();
            Double maxRentTotal = 0.0;

            rents = new GenericClass<>(JsonUtils.loadList("rents.json", Rent.class));
            properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));

            if (rents.returnList().isEmpty()) {
                System.out.println("The Sales file is empty");
                return null;
            }

            for (Rent r : rents.returnList()) {

                Property p = findPropertyById(r.getProperty().getId());
                if (p != null) {
                    Double rentTotal = r.calculateTotal(p);
                    if (rentTotal > maxRentTotal) {
                        maxRentTotal = rentTotal;
                        topRent = r;
                    }
                }
            }
            return topRent;

        } catch (Exception e) {
            System.out.println("Error finding the top rent: " + e.getMessage());
            return null;
        }
    }


    public Sale topSale() {
        try {

            Sale topSale = new Sale();
            double maxSaleTotal = 0.0;
            sales = new GenericClass<>(JsonUtils.loadList("sales.json", Sale.class));
            properties = new GenericClass<>(JsonUtils.loadList("properties.json", Property.class));

            if (sales.returnList().isEmpty()) {
                System.out.println("No hay ventas cargadas.");
                return null;
            }

            for (Sale s : sales.returnList()) {
                Property p = findPropertyById(s.getProperty().getId());
                if (p != null) {
                    Double saleTotal = s.calculateTotal(p);
                    if (saleTotal > maxSaleTotal) {
                        maxSaleTotal = saleTotal;
                        topSale = s;
                    }
                }
            }
            return topSale;

        } catch (Exception e) {
            System.out.println("Error finding the top sale: " + e.getMessage());
            return null;
        }
    }

    private Property findPropertyById(Integer propertyId) {
        for (Property property : properties.returnList()) {
            if (property.getId().equals(propertyId)) {
                return property;
            }
        }
        return null;
    }
}