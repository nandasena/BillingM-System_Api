package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseObject implements Serializable {


    @Basic
    @Column(name = "description")
    private String  description;

    @Basic
    @Column(name = "estimate_receive_date")
    private Date estimateReceiveDate;

    @Basic
    @Column(name = "total_amount")
    private double totalAmount;

    @Basic
    @Column(name = "total_discount")
    private double totalDiscount;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_supplier_id" ,nullable = false,referencedColumnName = "supplier_id")
    private Supplier supplier;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEstimateReceiveDate() {
        return estimateReceiveDate;
    }

    public void setEstimateReceiveDate(Date estimateReceiveDate) {
        this.estimateReceiveDate = estimateReceiveDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
