package com.createvision.sivilima.valuesObject;

import java.util.Date;

public class PaymentDetailVO {

    private Long invoiceId;
    private String typeCode;
    private String chequeNumber;
    private double amount;
    private String cardNumber;
    private Long invoiceAmount;
    private Long totalPaymentAmount;
    private Long balanceAmount;
    private double paidAmount;
    private String invoiceNumber;
    private String customerName;
    private Long paymentDetailId;
    private String chequeDate;
    private String description;
    private Long bankId;
    private int incomeOrCost;
    private Long supplierId;
    private Long customerId;
    private String paymentDate;
    private String invoiceDate;
    private String paymentType;
    private String bankName;

    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }

    public String getTypeCode() { return typeCode; }
    public void setTypeCode(String typeCode) { this.typeCode = typeCode; }

    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber;}

    public Long getInvoiceAmount() { return invoiceAmount; }
    public void setInvoiceAmount(Long invoiceAmount) { this.invoiceAmount = invoiceAmount; }

    public Long getTotalPaymentAmount() { return totalPaymentAmount; }
    public void setTotalPaymentAmount(Long totalPaymentAmount) { this.totalPaymentAmount = totalPaymentAmount; }

    public Long getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(Long balanceAmount) { this.balanceAmount = balanceAmount;}

    public double getPaidAmount() {return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount;}

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Long getPaymentDetailId() { return paymentDetailId; }
    public void setPaymentDetailId(Long paymentDetailId) { this.paymentDetailId = paymentDetailId; }

    public String getChequeDate() { return chequeDate; }
    public void setChequeDate(String chequeDate) { this.chequeDate = chequeDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getBankId() { return bankId; }
    public void setBankId(Long bankId) { this.bankId = bankId;}

    public int getIncomeOrCost() { return incomeOrCost; }
    public void setIncomeOrCost(int incomeOrCost) { this.incomeOrCost = incomeOrCost; }

    public Long getSupplierId() { return supplierId;}
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getInvoiceDate() {return invoiceDate;}
    public void setInvoiceDate(String invoiceDate) {this.invoiceDate = invoiceDate;}

    public String getPaymentType() {return paymentType;}
    public void setPaymentType(String paymentType) {this.paymentType = paymentType;}

    public String getBankName() {return bankName;}
    public void setBankName(String bankName) {this.bankName = bankName;}
}
