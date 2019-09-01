package com.createvision.sivilima.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseObject implements Serializable {

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Basic
    @Column(name = "total_amount")
    private double totalAmount;

    @Basic
    @Column(name = "advance_amount")
    private double advanceAmount;

    @Basic
    @Column(name = "delivary_date")
    private Date delivaryDate;

    @Basic
    @Column(name = "invoice_date")
    private Date invoiceDate;

    @Basic
    @Column(name ="balance_amount")
    private double balanceAmount;


    @Basic
    @Column(name = "invoice_number",unique = true, nullable = false)
    private String invoiceNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    @Fetch(FetchMode.SELECT)
    private Set<InvoiceItemDetail> invoiceItemDetails = new HashSet<InvoiceItemDetail>(0);


    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getAdvanceAmount() { return advanceAmount; }
    public void setAdvanceAmount(double advanceAmount) { this.advanceAmount = advanceAmount; }

    public Date getElivaryDate() { return delivaryDate; }
    public void setElivaryDate(Date delivaryDate) { this.delivaryDate = delivaryDate; }

    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(double balanceAmount) { this.balanceAmount = balanceAmount; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Set<InvoiceItemDetail> getInvoiceItemDetails() { return invoiceItemDetails; }
    public void setInvoiceItemDetails(Set<InvoiceItemDetail> invoiceItemDetails) { this.invoiceItemDetails = invoiceItemDetails; }
}
