package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoice_item_Details")
public class InvoiceItemDetail extends BaseObject implements Serializable {

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id",nullable = false)
    @JsonIgnore
    private Item item;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id",nullable = false)
    @JsonIgnore
    private Invoice invoice;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",nullable = false)
    @JsonIgnore
    private ItemDetail itemDetail;

    @Basic
    @Column(name = "selling_quantity")
    private double sellingQuantity;

    @Basic
    @Column(name = "item_discount")
    private double itemDiscount;

    @Basic
    @Column(name = "item_discount_percentage")
    private double itemDiscountPercentage;

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public Item getItem() { return item; }
    public void setItem(Item item) {this.item = item; }

    public ItemDetail getItemDetail() {return itemDetail;}
    public void setItemDetail(ItemDetail itemDetail) { this.itemDetail = itemDetail; }

    public double getSellingQuantity() { return sellingQuantity; }
    public void setSellingQuantity(double sellingQuantity) { this.sellingQuantity = sellingQuantity; }

    public double getItemDiscount() { return itemDiscount; }
    public void setItemDiscount(double itemDiscount) { this.itemDiscount = itemDiscount; }

    public double getItemDiscountPercentage() {return itemDiscountPercentage;}
    public void setItemDiscountPercentage(double itemDiscountPercentage) { this.itemDiscountPercentage = itemDiscountPercentage;}
}
