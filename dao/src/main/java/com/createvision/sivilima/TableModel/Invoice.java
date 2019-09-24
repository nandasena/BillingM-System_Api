package com.createvision.sivilima.TableModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class /**/Invoice extends BaseObject implements Serializable {


    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "total_amount")
    private double totalAmount;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "advance_amount")
    private double advanceAmount;

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    @Basic
    @Column(name = "delivary_date")
    private Date delivaryDate;

    public Date getElivaryDate() {
        return delivaryDate;
    }

    public void setElivaryDate(Date delivaryDate) {
        this.delivaryDate = delivaryDate;
    }

    @Basic
    @Column(name = "invoice_date")
    private Date invoiceDate;

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Basic
    @Column(name = "balance_amount")
    private double balanceAmount;

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }


    @Basic
    @Column(name = "invoice_number", unique = true, nullable = false)
    private String invoiceNumber;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    @Fetch(FetchMode.SELECT)
    private Set<InvoiceItemDetail> invoiceItemDetails = new HashSet<InvoiceItemDetail>(0);

    public Set<InvoiceItemDetail> getInvoiceItemDetails() {
        return invoiceItemDetails;
    }

    public void setInvoiceItemDetails(Set<InvoiceItemDetail> invoiceItemDetails) {
        this.invoiceItemDetails = invoiceItemDetails;
    }

    @Basic
    @Column(name = "is_delete",columnDefinition = "boolean default false")
    private Boolean isDelete;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
