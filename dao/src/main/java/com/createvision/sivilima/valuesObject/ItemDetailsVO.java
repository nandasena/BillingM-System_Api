package com.createvision.sivilima.valuesObject;

public class ItemDetailsVO {
    long id;
    double price;
    double quantity;
    double availableQuantity;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public double getPrice() { return price;}
    public void setPrice(double price) { this.price = price; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity;}

    public double getAvailableQuantity() { return availableQuantity;}
    public void setAvailableQuantity(double availableQuantity) { this.availableQuantity = availableQuantity;}
}
