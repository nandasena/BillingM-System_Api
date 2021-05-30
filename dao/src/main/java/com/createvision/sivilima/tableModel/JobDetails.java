package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "jobDetails")
public class JobDetails extends BaseObject implements Serializable {

    @Basic
    @Column(name = "description")
    private String description;
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    @Basic
    @Column(name = "expenses")
    private double expenses;
    public double getExpenses() {return expenses;}
    public void setExpenses(double expenses) {this.expenses = expenses;}

    @Basic
    @Column(name = "net_expenses")
    private double netExpenses;
    public double getNetExpenses() {return netExpenses;}
    public void setNetExpenses(double netExpenses) {this.netExpenses = netExpenses;}

    @Basic
    @Column(name = "expenses_type")
    @Enumerated(EnumType.STRING)
    private ExpensesType expensesType;
    public ExpensesType getExpensesType() {return expensesType;}
    public void setExpensesType(ExpensesType expensesType) {this.expensesType = expensesType;}

    @Basic
    @Column(name = "total_item_discount")
    private double totalItemDiscount;
    public double getTotalItemDiscount() {return totalItemDiscount;}
    public void setTotalItemDiscount(double totalItemDiscount) {this.totalItemDiscount = totalItemDiscount;}

    @Basic
    @Column(name = "item_cost")
    private double itemCost;
    public double getItemCost() {return itemCost;}
    public void setItemCost(double itemCost) {this.itemCost = itemCost;}

    @Basic
    @Column(name = "item_qty")
    private double itemQuantity;
    public double getItemQuantity() {return itemQuantity;}
    public void setItemQuantity(double itemQuantity) {this.itemQuantity = itemQuantity;}

    @Basic
    @Column(name = "received_qty",columnDefinition ="double default 0")
    private double receivedQty;
    public double getReceivedQty() {return receivedQty;}
    public void setReceivedQty(double receivedQty) {this.receivedQty = receivedQty;}

    @Enumerated(EnumType.STRING)
    private PriceType price_type;
    public PriceType getPrice_type() {return price_type;}
    public void setPrice_type(PriceType price_type) {this.price_type = price_type;}

    @Enumerated(EnumType.STRING)
    private DiscountType discount_type;
    public DiscountType getDiscount_type() {return discount_type;}
    public void setDiscount_type(DiscountType discount_type) {this.discount_type = discount_type;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;
    public Item getItem() {return item;}
    public void setItem(Item item) {this.item = item;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id")
    @JsonIgnore
    private ItemDetail itemDetail;
    public ItemDetail getItemDetail() {return itemDetail;}
    public void setItemDetail(ItemDetail itemDetail) {this.itemDetail = itemDetail;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id",nullable = false)
    @JsonIgnore
    private Job job;
    public Job getJob() {return job;}
    public void setJob(Job job) {this.job = job;}
}
