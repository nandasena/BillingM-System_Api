package com.createvision.sivilima.tableModel;

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
    @Column(name = "customer_name")
    private String customerName;
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName;}

    @Basic
    @Column(name = "invoice_discount")
    private double invoiceDiscount;
    public double getInvoiceDiscount() {return invoiceDiscount;}
    public void setInvoiceDiscount(double invoiceDiscount) { this.invoiceDiscount = invoiceDiscount; }

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
    @Column(name = "total_discount")
    private double totalDiscount;
    public double getTotalDiscount() { return totalDiscount; }
    public void setTotalDiscount(double totalDiscount) { this.totalDiscount = totalDiscount; }

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
    public void setInvoiceItemDetails(Set<InvoiceItemDetail> invoiceItemDetails) { this.invoiceItemDetails = invoiceItemDetails; }

    @Basic
    @Column(name = "is_delete",columnDefinition = "boolean default false")
    private Boolean isDelete;
    public Boolean getDelete() {
        return isDelete;
    }
    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_payment_type_id",nullable = false,referencedColumnName = "payment_type_id")
    private PaymentType paymentTypeId;
    public PaymentType getPaymentTypeId() { return paymentTypeId; }
    public void setPaymentTypeId(PaymentType paymentTypeId) { this.paymentTypeId = paymentTypeId; }

    @Basic
    @Column(name = "cheque_number")
    private String chequeNumber;
    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }
}
