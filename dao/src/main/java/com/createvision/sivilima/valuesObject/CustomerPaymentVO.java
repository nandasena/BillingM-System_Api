package com.createvision.sivilima.valuesObject;

public class CustomerPaymentVO {
    private double creditAmount;
    private double debitAmount;
    private String createDate;
    private String paymentDate;
    private Long invoiceId;
    private String description;
    private String paymentType;

    public double getCreditAmount() { return creditAmount; }
    public void setCreditAmount(double creditAmount) { this.creditAmount = creditAmount; }

    public double getDebitAmount() { return debitAmount; }
    public void setDebitAmount(double debitAmount) { this.debitAmount = debitAmount; }

    public String getCreateDate() { return createDate; }
    public void setCreateDate(String createDate) { this.createDate = createDate; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description;}

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
}
