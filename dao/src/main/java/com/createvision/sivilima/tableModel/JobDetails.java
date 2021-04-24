package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "jobDetails")
public class JobDetails extends BaseObject implements Serializable {


    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",nullable = false)
    @JsonIgnore
    private Item item;
    public Item getItem() {return item;}
    public void setItem(Item item) {this.item = item;}

    @Basic
    @Column(name = "item_cost")
    private double itemCost;
    public double getItemCost() {return itemCost;}
    public void setItemCost(double itemCost) {this.itemCost = itemCost;}

    @Basic
    @Column(name = "expenses")
    private double expenses;
    public double getExpenses() {return expenses;}
    public void setExpenses(double expenses) {this.expenses = expenses;}


    @Basic
    @Column(name = "wages")
    @Enumerated(EnumType.STRING)
    private ExpensesType expensesType;
    public ExpensesType getExpensesType() {return expensesType;}
    public void setExpensesType(ExpensesType expensesType) {this.expensesType = expensesType;}

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id",nullable = false)
    @JsonIgnore
    private Jobs job;




}
