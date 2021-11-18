package com.createvision.sivilima.tableModel;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "invoice_item_return")
public class InvoiceItemReturn extends BaseObject implements Serializable {

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date returnDate;

    @Basic
    @Column(name = "reason_of_return")
    private String reasonOfReturn;

    @Basic
    @Column(name = "return_quantity",nullable = false, columnDefinition = "int default 0")
    private double returnQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",nullable = false)
    @JsonIgnore
    private ItemDetail itemDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id",nullable = false)
    @JsonIgnore
    private Invoice invoice;

    public Date getReturnDate() {return returnDate;}
    public void setReturnDate(Date returnDate) {this.returnDate = returnDate;}

    public String getReasonOfReturn() {return reasonOfReturn;}
    public void setReasonOfReturn(String reasonOfReturn) {this.reasonOfReturn = reasonOfReturn;}

    public double getReturnQuantity() {return returnQuantity;}
    public void setReturnQuantity(double returnQuantity) {this.returnQuantity = returnQuantity;}

    public ItemDetail getItemDetail() {return itemDetail;}
    public void setItemDetail(ItemDetail itemDetail) {this.itemDetail = itemDetail;}

    public Invoice getInvoice() {return invoice;}
    public void setInvoice(Invoice invoice) {this.invoice = invoice;}
}
