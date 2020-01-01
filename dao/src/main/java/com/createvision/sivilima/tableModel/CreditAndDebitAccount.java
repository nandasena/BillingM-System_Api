package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cre_deb_account")
public class CreditAndDebitAccount extends BaseObject implements Serializable {
    @Basic
    @Column(name = "payment_description")
    private String paymentDescription;

    @Basic
    @Column(name = "credit")
    private double credit;

    @Basic
    @Column(name = "debit")
    private double debit;

    @Basic
    @Column(name = "income_or_cost")
    private String incomeOrCost;

    @Basic
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
    @JoinColumn(name = "fk_customer_id")
    @JsonIgnore
    private Customer customer;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_invoice_id")
    @JsonIgnore
    private Invoice invoice;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_purchase_order_id")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cheque_detail_id")
    @JsonIgnore
    private ChequePaymentDetail chequePaymentDetail;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_bank_id")
    @JsonIgnore
    private BankDetail bankDetail;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_debtor_id")
    @JsonIgnore
    private Debtor debtor;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_creditor_id")
    @JsonIgnore
    private Creditor creditor;

    public String getPaymentDescription() { return paymentDescription; }
    public void setPaymentDescription(String paymentDescription) { this.paymentDescription = paymentDescription; }

    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }

    public double getDebit() {return debit; }
    public void setDebit(double debit) { this.debit = debit; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public PurchaseOrder getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) { this.purchaseOrder = purchaseOrder; }

    public ChequePaymentDetail getChequePaymentDetail() { return chequePaymentDetail; }
    public void setChequePaymentDetail(ChequePaymentDetail chequePaymentDetail) { this.chequePaymentDetail = chequePaymentDetail;}

    public BankDetail getBankDetail() { return bankDetail; }
    public void setBankDetail(BankDetail bankDetail) { this.bankDetail = bankDetail; }

    public String getIncomeOrCost() { return incomeOrCost; }
    public void setIncomeOrCost(String incomeOrCost) { this.incomeOrCost = incomeOrCost; }

    public Debtor getDebtor() { return debtor; }
    public void setDebtor(Debtor debtor) { this.debtor = debtor; }

    public Creditor getCreditor() { return creditor; }
    public void setCreditor(Creditor creditor) { this.creditor = creditor; }
}
