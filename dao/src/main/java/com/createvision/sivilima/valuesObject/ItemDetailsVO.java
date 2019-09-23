package com.createvision.sivilima.valuesObject;

public class ItemDetailsVO {
    long itemDetailId;
    double sellingPrice;
    double costPrice;
    double quantity;
    double availableQuantity;


    public long getItemDetailId() { return itemDetailId;}
    public void setItemDetailId(long itemDetailId) { this.itemDetailId = itemDetailId; }

    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice;}

    public double getCostPrice() { return costPrice;}
    public void setCostPrice(double costPrice) { this.costPrice = costPrice; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity;}

    public double getAvailableQuantity() { return availableQuantity;}
    public void setAvailableQuantity(double availableQuantity) { this.availableQuantity = availableQuantity;}
}
