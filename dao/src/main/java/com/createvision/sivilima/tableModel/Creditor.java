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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_payment_type_code",nullable = false,referencedColumnName = "type_code")
    private PaymentMethod paymentMethod;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_supplier_id")
    @JsonIgnore
    private Supplier supplier;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_good_received_id")
    @JsonIgnore
    private GoodReceived goodReceived;

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

    public GoodReceived getGoodReceived() {return goodReceived;}
    public void setGoodReceived(GoodReceived goodReceived) {this.goodReceived = goodReceived;}

    public PaymentMethod getPaymentMethod() {return paymentMethod;}
    public void setPaymentMethod(PaymentMethod paymentMethod) {this.paymentMethod = paymentMethod;}
}

