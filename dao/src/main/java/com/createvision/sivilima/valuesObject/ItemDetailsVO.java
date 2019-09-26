package com.createvision.sivilima.valuesObject;

public class ItemDetailsVO {
    long itemDetailId;
    double sellingPrice;
    double costPrice;
    double quantity;
    double availableQuantity;
    long itemId;
    Long companyId;
    String purchaseDate;
    boolean isDelete;


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

    public long getItemId() { return itemId; }
    public void setItemId(long itemId) { this.itemId = itemId; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId;}

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public boolean isDelete() { return isDelete;}
    public void setDelete(boolean delete) { isDelete = delete;}
}
