package com.createvision.sivilims.valueobject;

import com.createvision.sivilima.model.BaseObject;
import com.createvision.sivilima.model.InvoiceItemDetail;
import com.createvision.sivilima.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InvoiceVO extends BaseObjectVO implements Serializable {

   // private UserVO user;
    private double totalAmount;
    private double advanceAmount;
    private Date delivaryDate;
    private Date invoiceDate;
    private double balanceAmount;
    private String invoiceNumber;
    private Set<InvoiceItemDetail> invoiceItemDetails = new HashSet<InvoiceItemDetail>(0);
    private Long userId;
    private List<ItemVO> itemList;


//    public UserVO getUser() { return user; }
//    public void setUser(UserVO user) {
//        this.user = user;
//    }

    public Date getDelivaryDate() { return delivaryDate; }
    public void setDelivaryDate(Date delivaryDate) { this.delivaryDate = delivaryDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getAdvanceAmount() { return advanceAmount; }
    public void setAdvanceAmount(double advanceAmount) { this.advanceAmount = advanceAmount; }

    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(double balanceAmount) { this.balanceAmount = balanceAmount; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Set<InvoiceItemDetail> getInvoiceItemDetails() { return invoiceItemDetails; }
    public void setInvoiceItemDetails(Set<InvoiceItemDetail> invoiceItemDetails) { this.invoiceItemDetails = invoiceItemDetails; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<ItemVO> getItemList() { return itemList; }
    public void setItemList(List<ItemVO> itemList) { this.itemList = itemList; }
}
