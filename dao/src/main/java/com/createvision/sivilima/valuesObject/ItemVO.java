package com.createvision.sivilima.valuesObject;

import java.util.Set;

public class ItemVO {
    private  Long itemId;
    private  String itemName;
    private  String description;
    private  Long subCategoryId;
    private  Long itemDetailId;
    private  double sellingQuantity;
    private  Set<ItemDetailsVO> itemDetailList;
    private String itemCode;
    private double itemDiscount;
    private double discountPercentage;
    private double total;
    private double price;

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
}
