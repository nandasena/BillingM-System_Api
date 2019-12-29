package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "return_item_detail")
public class ReturnItemDetail extends BaseObject implements Serializable {

    @Basic
    @Column(name = "description")
    private String  description;

    @Basic
    @Column(name = "quantity")
    private double quantity;

    @Basic
    @Column(name = "date")
    private Date date;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_purchase_order_id")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_invoice_id")
    @JsonIgnore
    private Invoice invoice;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_return_type_id")
    @JsonIgnore
    private ReturnType returnType;


    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item_detail_id")
    @JsonIgnore
    private ItemDetail itemDetail;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public PurchaseOrder getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) { this.purchaseOrder = purchaseOrder; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public ReturnType getReturnType() { return returnType; }
    public void setReturnType(ReturnType returnType) { this.returnType = returnType; }

    public ItemDetail getItemDetail() { return itemDetail; }
    public void setItemDetail(ItemDetail itemDetail) { this.itemDetail = itemDetail; }
}
