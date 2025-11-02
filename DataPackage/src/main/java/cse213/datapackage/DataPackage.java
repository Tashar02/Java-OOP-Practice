package cse213.datapackage;

import java.time.LocalDate;

public class DataPackage {
    private String packageName, validity, availability;
    private int dataAmount, price;
    private LocalDate offerEndsDate;

    public DataPackage(String packageName, String validity, String availability, int dataAmount, int price, LocalDate offerEndsDate) {
        this.packageName = packageName;
        this.validity = validity;
        this.availability = availability;
        this.dataAmount = dataAmount;
        this.price = price;
        this.offerEndsDate = offerEndsDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getDataAmount() {
        return dataAmount;
    }

    public void setDataAmount(int dataAmount) {
        this.dataAmount = dataAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getOfferEndsDate() {
        return offerEndsDate;
    }

    public void setOfferEndsDate(LocalDate offerEndsDate) {
        this.offerEndsDate = offerEndsDate;
    }

    @Override
    public String toString() {
        return "DataPackage{" +
                "packageName='" + packageName + '\'' +
                ", validity='" + validity + '\'' +
                ", availability='" + availability + '\'' +
                ", dataAmount=" + dataAmount +
                ", price=" + price +
                ", offerEndsDate=" + offerEndsDate +
                '}';
    }
}
