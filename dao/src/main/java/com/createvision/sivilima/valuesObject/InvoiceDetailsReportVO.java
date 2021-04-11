package com.createvision.sivilima.valuesObject;

public class InvoiceDetailsReportVO {
   private Long invoiceId;
   private String invoiceNumber;
   private double totalAmount;
   private double invoiceDiscount;
   private double totalDiscount;
   private String invoiceDate;
   private double itemPrice;
   private double sellingQuantity;
   private double totalItemDiscount;
   private double itemCost;
   private String discountType;

    public Long getInvoiceId() {return invoiceId;}
    public void setInvoiceId(Long invoiceId) {this.invoiceId = invoiceId;}

    public String getInvoiceNumber() {return invoiceNumber;}
    public void setInvoiceNumber(String invoiceNumber) {this.invoiceNumber = invoiceNumber;}

    public double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public double getInvoiceDiscount() {return invoiceDiscount;}
    public void setInvoiceDiscount(double invoiceDiscount) {this.invoiceDiscount = invoiceDiscount;}

    public double getTotalDiscount() {return totalDiscount;}
    public void setTotalDiscount(double totalDiscount) {this.totalDiscount = totalDiscount;}

    public String getInvoiceDate() {return invoiceDate;}
    public void setInvoiceDate(String invoiceDate) {this.invoiceDate = invoiceDate;}

    public double getItemPrice() {return itemPrice;}
    public void setItemPrice(double itemPrice) {this.itemPrice = itemPrice;}

    public double getSellingQuantity() {return sellingQuantity;}
    public void setSellingQuantity(double sellingQuantity) {this.sellingQuantity = sellingQuantity;}

    public double getTotalItemDiscount() {return totalItemDiscount;}
    public void setTotalItemDiscount(double totalItemDiscount) {this.totalItemDiscount = totalItemDiscount;}

    public double getItemCost() {return itemCost;}
    public void setItemCost(double itemCost) {this.itemCost = itemCost;}

    public String getDiscountType() {return discountType;}
    public void setDiscountType(String discountType) {this.discountType = discountType;}
}
