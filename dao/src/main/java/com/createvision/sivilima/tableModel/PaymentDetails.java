package com.createvision.sivilima.tableModel;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "payment_detail")
public class PaymentDetails extends BaseObject implements Serializable {

    @Basic
    @Column(name = "cheque_number")
    private String chequeNumber;

    @Basic
    @Column(name = "amount")
    private double amount;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_type_code",nullable = false,referencedColumnName = "type_code")
    private PaymentMethod paymentMethod;

    @Basic
    @Column(name = "card_number")
    private String cardNumber;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_invoice_id")
    private Invoice invoice;

    @Basic
    @Column(name = "is_clear",columnDefinition = "boolean default false")
    private boolean isClear;

    @Basic
    @Column(name = "cheque_date")
    private Date chequeDate;

    @Basic
    @Column(name = "cheque_description")
    private String chequeDescription;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_bank_id")
    private BankDetail bankDetail;

    @Basic
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cheque_detail_id")
    private ChequePaymentDetail chequePaymentDetail;





    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public boolean isClear() { return isClear; }
    public void setClear(boolean clear) { isClear = clear; }

    public Date getChequeDate() {return chequeDate; }
    public void setChequeDate(Date chequeDate) { this.chequeDate = chequeDate; }

    public String getChequeDescription() { return chequeDescription; }
    public void setChequeDescription(String chequeDescription) { this.chequeDescription = chequeDescription; }

    public BankDetail getBankDetail() { return bankDetail; }
    public void setBankDetail(BankDetail bankDetail) { this.bankDetail = bankDetail; }

    public ChequePaymentDetail getChequePaymentDetail() {return chequePaymentDetail;}
    public void setChequePaymentDetail(ChequePaymentDetail chequePaymentDetail) {this.chequePaymentDetail = chequePaymentDetail;}
}
