package com.createvision.sivilima.valuesObject;

public class PaymentDetailVO {

    private Long invoiceId;
    private String typeCode;
    private String chequeNumber;
    private Long amount;
    private String cardNumber;
    private Long invoiceAmount;
    private Long totalPaymentAmount;
    private Long balanceAmount;

    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }

    public String getTypeCode() { return typeCode; }
    public void setTypeCode(String typeCode) { this.typeCode = typeCode; }

    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber;}

    public Long getInvoiceAmount() { return invoiceAmount; }
    public void setInvoiceAmount(Long invoiceAmount) { this.invoiceAmount = invoiceAmount; }

    public Long getTotalPaymentAmount() { return totalPaymentAmount; }
    public void setTotalPaymentAmount(Long totalPaymentAmount) { this.totalPaymentAmount = totalPaymentAmount; }

    public Long getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(Long balanceAmount) { this.balanceAmount = balanceAmount;}
}
