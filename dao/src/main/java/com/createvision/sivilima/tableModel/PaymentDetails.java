package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment_detail")
public class PaymentDetails extends BaseObject implements Serializable {

    @Basic
    @Column(name = "cheque_number")
    private String chequeNumber;

    @Basic
    @Column(name = "amount")
    private Long amount;

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


    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }
}