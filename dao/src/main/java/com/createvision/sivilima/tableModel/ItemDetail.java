package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "item_details")
public class ItemDetail extends BaseObject implements Serializable {

    @Basic
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Basic
    @Column(name = "fabricator_price")
    private double fabricatorPrice;

    @Basic
    @Column(name = "mrp_price")
    private double mrpPrice;

    @Basic
    @Column(name = "customer_price")
    private double customerPrice;

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

    @Basic
    @Column(name = "is_delete",nullable = false,columnDefinition = "boolean default false")
    private boolean isDelete;


    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_brand_id",nullable = false)
    @JsonIgnore
    private Brand brand;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_supplier_id",nullable = false)
    @JsonIgnore
    private Supplier supplier;



    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public double getFabricatorPrice() { return fabricatorPrice; }
    public void setFabricatorPrice(double fabricatorPrice) { this.fabricatorPrice = fabricatorPrice; }

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

    public boolean isDelete() { return isDelete; }
    public void setDelete(boolean delete) { isDelete = delete;}

    public double getMrpPrice() { return mrpPrice; }
    public void setMrpPrice(double mrpPrice) { this.mrpPrice = mrpPrice; }

    public double getCustomerPrice() { return customerPrice; }
    public void setCustomerPrice(double customerPrice) { this.customerPrice = customerPrice; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }


}
