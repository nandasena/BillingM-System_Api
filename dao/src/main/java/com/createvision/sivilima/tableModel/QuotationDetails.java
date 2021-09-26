package com.createvision.sivilima.tableModel;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "quotation_item_Details")
public class QuotationDetails extends BaseObject implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",nullable = false)
    @JsonIgnore
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotation_id",nullable = false)
    @JsonIgnore
    private Quotation quotation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",nullable = false)
    @JsonIgnore
    private ItemDetail itemDetail;

    @Basic
    @Column(name = "selling_quantity")
    private double sellingQuantity;

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

    public Quotation getQuotation() {return quotation;}
    public void setQuotation(Quotation quotation) {this.quotation = quotation;}

    public Item getItem() { return item; }
    public void setItem(Item item) {this.item = item;}

    public ItemDetail getItemDetail() {return itemDetail;}
    public void setItemDetail(ItemDetail itemDetail) { this.itemDetail = itemDetail; }

    public double getSellingQuantity() { return sellingQuantity; }
    public void setSellingQuantity(double sellingQuantity) { this.sellingQuantity = sellingQuantity; }

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
