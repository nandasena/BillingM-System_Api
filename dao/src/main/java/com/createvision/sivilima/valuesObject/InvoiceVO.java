package com.createvision.sivilima.valuesObject;

import com.createvision.sivilima.tableModel.InvoiceItemDetail;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InvoiceVO {
    private double totalAmount;
    private double advanceAmount;
    private Date delivaryDate;
    private String invoiceDate;
    private double balanceAmount;
    private String invoiceNumber;
    private Set<InvoiceItemDetail> invoiceItemDetails = new HashSet<InvoiceItemDetail>(0);
    private Long userId;
    private List<ItemVO> itemList;
    private Long id;
    private String customerName;
    private String invoiceDateOfString;
    private double invoiceDiscount;
    private Long customerId;
    private List<PaymentDetailVO> paymentDetailList;
    private String paymentType;
    private TempCustomerVO tempCustomerVO;
    private String returnDescription;
    private String returnDate;

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getAdvanceAmount() { return advanceAmount; }
    public void setAdvanceAmount(double advanceAmount) { this.advanceAmount = advanceAmount; }

    public Date getDelivaryDate() { return delivaryDate; }
    public void setDelivaryDate(Date delivaryDate) { this.delivaryDate = delivaryDate; }

    public String getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(String invoiceDate) { this.invoiceDate = invoiceDate;}

    public double getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(double balanceAmount) { this.balanceAmount = balanceAmount; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Set<InvoiceItemDetail> getInvoiceItemDetails() { return invoiceItemDetails; }
    public void setInvoiceItemDetails(Set<InvoiceItemDetail> invoiceItemDetails) { this.invoiceItemDetails = invoiceItemDetails;}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<ItemVO> getItemList() { return itemList; }
    public void setItemList(List<ItemVO> itemList) { this.itemList = itemList; }

    public Long getId() {return id;}
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getInvoiceDateOfString() { return invoiceDateOfString; }
    public void setInvoiceDateOfString(String invoiceDateOfString) { this.invoiceDateOfString = invoiceDateOfString; }

    public double getInvoiceDiscount() { return invoiceDiscount; }
    public void setInvoiceDiscount(double invoiceDiscount) { this.invoiceDiscount = invoiceDiscount; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId;}

    public List<PaymentDetailVO> getPaymentDetailList() { return paymentDetailList; }
    public void setPaymentDetailList(List<PaymentDetailVO> paymentDetailList) {this.paymentDetailList = paymentDetailList;}

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType;}

    public TempCustomerVO getTempCustomerVO() {return tempCustomerVO;}
    public void setTempCustomerVO(TempCustomerVO tempCustomerVO) {this.tempCustomerVO = tempCustomerVO;}

    public String getReturnDescription() {return returnDescription;}
    public void setReturnDescription(String returnDescription) {this.returnDescription = returnDescription;}

    public String getReturnDate() {return returnDate;}
    public void setReturnDate(String returnDate) {this.returnDate = returnDate;}
}
