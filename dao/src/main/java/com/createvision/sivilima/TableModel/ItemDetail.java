package com.createvision.sivilima.TableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "item_details")
public class ItemDetail extends BaseObject implements Serializable {

    @Basic
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Basic
    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "cost_price",nullable = false)
    private double costPrice;

    @Basic
    @Column(name = "quantity")
    private double quantity;

    @Basic
    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Basic
    @Column(name = "available_quantity",nullable = false)
    private double availableQuantity;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",nullable = false)
    @JsonIgnore
    private CompanyDetail companyDetail;


    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }

    public double getAvailableQuantity() {return availableQuantity;}
    public void setAvailableQuantity(double availableQuantity) { this.availableQuantity = availableQuantity; }

    public double getCostPrice() {return costPrice; }
    public void setCostPrice(double costPrice) {this.costPrice = costPrice;}

    public CompanyDetail getCompanyDetail() { return companyDetail; }
    public void setCompanyDetail(CompanyDetail companyDetail) { this.companyDetail = companyDetail; }
}
