package com.createvision.sivilima.valuesObject;

public class ItemDetailsVO {
    private  long itemDetailId;
    private  double fabricatorPrice;
    private double mrpPrice;
    private double customerPrice;
    private  double costPrice;
    private double quantity;
    private double availableQuantity;
    private  long itemId;
    private Long companyId;
    private String purchaseDate;
    private boolean isDelete;
    private String itemName;
    private double totalItemAmount;
    private double totalItemDiscount;
    private double receivedQuantity;
    private double receiveQuantity;
    private long brandId;
    private long supplierId;




    public long getItemDetailId() { return itemDetailId;}
    public void setItemDetailId(long itemDetailId) { this.itemDetailId = itemDetailId; }

    public double getFabricatorPrice() { return fabricatorPrice; }
    public void setFabricatorPrice(double fabricatorPrice) { this.fabricatorPrice = fabricatorPrice;}

    public double getMrpPrice() {return mrpPrice;}
    public void setMrpPrice(double mrpPrice) { this.mrpPrice = mrpPrice; }

    public double getCustomerPrice() { return customerPrice;}
    public void setCustomerPrice(double customerPrice) { this.customerPrice = customerPrice;}

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

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public double getTotalItemAmount() { return totalItemAmount; }
    public void setTotalItemAmount(double totalItemAmount) { this.totalItemAmount = totalItemAmount; }

    public double getTotalItemDiscount() { return totalItemDiscount; }
    public void setTotalItemDiscount(double totalItemDiscount) { this.totalItemDiscount = totalItemDiscount; }

    public double getReceivedQuantity() { return receivedQuantity; }
    public void setReceivedQuantity(double receivedQuantity) { this.receivedQuantity = receivedQuantity; }

    public double getReceiveQuantity() { return receiveQuantity;}
    public void setReceiveQuantity(double receiveQuantity) {this.receiveQuantity = receiveQuantity;}

    public long getBrandId() {return brandId;}
    public void setBrandId(long brandId) {this.brandId = brandId;}

    public long getSupplierId() {return supplierId;}
    public void setSupplierId(long supplierId) {this.supplierId = supplierId;}
}
