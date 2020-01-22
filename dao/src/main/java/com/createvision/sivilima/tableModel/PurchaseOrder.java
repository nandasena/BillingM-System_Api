package com.createvision.sivilima.tableModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    @Column(name = "purchase_code")
    private String purchaseCode;

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
    @JoinColumn(name = "fk_branch_id")
    private Branch branch;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrder")
    @Fetch(FetchMode.SELECT)
    private List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>(0);


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getEstimateReceiveDate() { return estimateReceiveDate; }
    public void setEstimateReceiveDate(Date estimateReceiveDate) { this.estimateReceiveDate = estimateReceiveDate; }

    public String getPurchaseCode() { return purchaseCode; }
    public void setPurchaseCode(String purchaseCode) { this.purchaseCode = purchaseCode; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getTotalDiscount() { return totalDiscount; }
    public void setTotalDiscount(double totalDiscount) { this.totalDiscount = totalDiscount; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<PurchaseOrderDetail> getPurchaseOrderDetails() { return purchaseOrderDetails; }
    public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) { this.purchaseOrderDetails = purchaseOrderDetails; }

    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }
}
