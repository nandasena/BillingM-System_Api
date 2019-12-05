package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;

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
}
