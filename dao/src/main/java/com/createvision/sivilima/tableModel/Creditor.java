package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "creditor")
public class Creditor extends BaseObject implements Serializable {

    @Basic
    @Column(name = "credit")
    private double credit;

    @Basic
    @Column(name = "debit")
    private double debit;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "payment_date")
    private Date paymentDate;


    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_invoice_id")
    @JsonIgnore
    private Invoice invoice;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_supplier_id")
    @JsonIgnore
    private Supplier supplier;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_purchase_order_id")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;

    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }

    public double getDebit() { return debit; }
    public void setDebit(double debit) { this.debit = debit; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date creditDate) { this.paymentDate = creditDate; }


    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public PurchaseOrder getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) { this.purchaseOrder = purchaseOrder; }
}

