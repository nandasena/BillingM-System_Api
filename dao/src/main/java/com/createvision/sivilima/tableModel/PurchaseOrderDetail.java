package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "purchase_order_detail")
public class PurchaseOrderDetail extends BaseObject implements Serializable {

    @Basic
    @Column(name = "price")
    private double  price;

    @Basic
    @Column(name = "qty")
    private double  qty;

    @Basic
    @Column(name = "total")
    private double  total;

    @Basic
    @Column(name = "total_discount")
    private double  totalDiscount;

    @Basic
    @Column(name = "discount_percentage")
    private double  discountPercentage;

    @Basic
    @Column(name = "received_qty",columnDefinition = "double default 0")
    private double  receivedQTY;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item_id")
    private Item item;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item_detail_id")
    private ItemDetail itemDetail;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_purchase_order_id")
    private PurchaseOrder purchaseOrder;




    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getTotalDiscount() { return totalDiscount; }
    public void setTotalDiscount(double totalDiscount) { this.totalDiscount = totalDiscount; }

    public double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }

    public double getReceivedQTY() { return receivedQTY; }
    public void setReceivedQTY(double receivedQTY) { this.receivedQTY = receivedQTY; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public PurchaseOrder getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) { this.purchaseOrder = purchaseOrder; }

    public ItemDetail getItemDetail() {return itemDetail;}
    public void setItemDetail(ItemDetail itemDetail) {this.itemDetail = itemDetail;}
}
