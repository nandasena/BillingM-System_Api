package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "payment_detail_of_credit")
public class PaymentDetailsOfCredit extends BaseObject implements Serializable {

    @Basic
    @Column(name = "payee")
    private String payee;

    @Basic
    @Column(name = "id_number")
    private String idNumber;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "amount")
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_type_code",nullable = false,referencedColumnName = "type_code")
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_payment_detail_id")
    private PaymentDetails paymentDetails;

    @Basic
    @Column(name = "cheque_number")
    private String chequeNumber;

    @Basic
    @Column(name = "card_number")
    private String cardNumber;

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



    public String getPayee() { return payee; }
    public void setPayee(String payee) { this.payee = payee; }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public PaymentDetails getPaymentDetails() { return paymentDetails; }
    public void setPaymentDetails(PaymentDetails paymentDetails) { this.paymentDetails = paymentDetails; }

    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public boolean isClear() { return isClear; }
    public void setClear(boolean clear) { isClear = clear; }

    public Date getChequeDate() { return chequeDate; }
    public void setChequeDate(Date chequeDate) { this.chequeDate = chequeDate; }

    public String getChequeDescription() { return chequeDescription; }
    public void setChequeDescription(String chequeDescription) { this.chequeDescription = chequeDescription; }

    public BankDetail getBankDetail() { return bankDetail; }
    public void setBankDetail(BankDetail bankDetail) { this.bankDetail = bankDetail; }
}
