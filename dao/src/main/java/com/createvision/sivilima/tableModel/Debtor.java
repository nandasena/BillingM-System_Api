package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "debtor")
public class Debtor extends BaseObject implements Serializable {

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
    @JoinColumn(name = "fk_customer_id")
    @JsonIgnore
    private Customer customer;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_invoice_id")
    @JsonIgnore
    private Invoice invoice;

    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }

    public double getDebit() { return debit; }
    public void setDebit(double debit) { this.debit = debit; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date debitDate) { this.paymentDate = debitDate; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }
}
