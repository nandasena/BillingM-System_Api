package com.createvision.sivilima.valuesObject;

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
}
