package com.createvision.sivilima.valuesObject;

import java.util.Set;

public class ItemVO {
    private  Long itemId;
    private  String itemName;
    private  String description;
    private  Long subCategoryId;
    private  Long itemDetailId;
    private  double sellingQuantity;
    private  double receivedQuantity;
    private  Set<ItemDetailsVO> itemDetailList;
    private String itemCode;
    private double itemDiscount;
    private double discountPercentage;
    private double total;
    private double price;
    private double orderQuantity;
    private int typeOfPrice;
    private int typeOfDiscount;
    private double priceDiscount;
    private String expensesType;
    private String returnDescription;

    public Long getItemId() {return itemId;}
    public void setItemId(Long itemId) {this.itemId = itemId;}

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getSubCategoryId() { return subCategoryId; }
    public void setSubCategoryId(Long subCategoryId) { this.subCategoryId = subCategoryId; }

    public Long getItemDetailId() { return itemDetailId; }
    public void setItemDetailId(Long itemDetailId) { this.itemDetailId = itemDetailId; }

    public double getSellingQuantity() { return sellingQuantity; }
    public void setSellingQuantity(double sellingQuantity) { this.sellingQuantity = sellingQuantity; }

    public double getReceivedQuantity() {return receivedQuantity;}
    public void setReceivedQuantity(double receivedQuantity) {this.receivedQuantity = receivedQuantity;}

    public Set<ItemDetailsVO> getItemDetailList() { return itemDetailList; }
    public void setItemDetailList(Set<ItemDetailsVO> itemDetailList) { this.itemDetailList = itemDetailList; }

    public String getItemCode() { return itemCode;}
    public void setItemCode(String itemCode) { this.itemCode = itemCode;}

    public double getItemDiscount() { return itemDiscount; }
    public void setItemDiscount(double itemDiscount) { this.itemDiscount = itemDiscount; }

    public double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total;}

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price;}

    public double getOrderQuantity() { return orderQuantity; }
    public void setOrderQuantity(double orderQuantity) { this.orderQuantity = orderQuantity; }

    public int getTypeOfPrice() {return typeOfPrice;}
    public void setTypeOfPrice(int typeOfPrice) {this.typeOfPrice = typeOfPrice;}

    public int getTypeOfDiscount() {return typeOfDiscount;}
    public void setTypeOfDiscount(int typeOfDiscount) {this.typeOfDiscount = typeOfDiscount;}

    public double getPriceDiscount() {return priceDiscount;}
    public void setPriceDiscount(double priceDiscount) {this.priceDiscount = priceDiscount;}

    public String getExpensesType() {return expensesType;}
    public void setExpensesType(String expensesType) {this.expensesType = expensesType;}

    public String getReturnDescription() {return returnDescription;}
    public void setReturnDescription(String returnDescription) {this.returnDescription = returnDescription;
    }
}
