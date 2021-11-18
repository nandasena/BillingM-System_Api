package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoice_item_Details")
public class InvoiceItemDetail extends BaseObject implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id",nullable = false)
    @JsonIgnore
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id",nullable = false)
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",nullable = false)
    @JsonIgnore
    private ItemDetail itemDetail;

    @Basic
    @Column(name = "selling_quantity")
    private double sellingQuantity;

    @Basic
    @Column(name = "return_quantity",nullable = false, columnDefinition = "int default 0")
    private double returnQuantity;

    @Basic
    @Column(name = "total_item_discount")
    private double totalItemDiscount;

    @Basic
    @Column(name = "item_discount_percentage")
    private double itemDiscountPercentage;

    @Basic
    @Column(name = "item_price")
    private double itemPrice;

    @Basic
    @Column(name = "total_amount")
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private PriceType price_type;

    @Enumerated(EnumType.STRING)
    private DiscountType discount_type;

    @Basic
    @Column(name = "item_cost")
    private double itemCost;

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public Item getItem() { return item; }
    public void setItem(Item item) {this.item = item;}

    public ItemDetail getItemDetail() {return itemDetail;}
    public void setItemDetail(ItemDetail itemDetail) { this.itemDetail = itemDetail; }

    public double getSellingQuantity() { return sellingQuantity; }
    public void setSellingQuantity(double sellingQuantity) { this.sellingQuantity = sellingQuantity; }

    public double getReturnQuantity() {return returnQuantity;}
    public void setReturnQuantity(double returnQuantity) {this.returnQuantity = returnQuantity;}

    public double getTotalItemDiscount() { return totalItemDiscount; }
    public void setTotalItemDiscount(double totalItemDiscount) { this.totalItemDiscount = totalItemDiscount; }

    public double getItemDiscountPercentage() {return itemDiscountPercentage;}
    public void setItemDiscountPercentage(double itemDiscountPercentage) { this.itemDiscountPercentage = itemDiscountPercentage;}

    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount;}

    public PriceType getPrice_type() {return price_type;}
    public void setPrice_type(PriceType price_type) {this.price_type = price_type;}

    public DiscountType getDiscount_type() {return discount_type;}
    public void setDiscount_type(DiscountType discount_type) {this.discount_type = discount_type;}

    public double getItemCost() {return itemCost;}
    public void setItemCost(double itemCost) {this.itemCost = itemCost;}
}
