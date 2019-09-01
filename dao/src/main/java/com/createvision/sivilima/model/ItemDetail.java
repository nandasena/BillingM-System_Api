package com.createvision.sivilima.model;

import org.hibernate.annotations.OptimisticLock;
import org.jboss.logging.LoggingClass;

import javax.naming.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "item_details")
public class ItemDetail extends BaseObject implements Serializable {

    @Basic
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    @Basic
    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "quantity")
    private double quantity;

    @Basic
    @Column(name = "purchase_date")
    private Date purchaseDate;

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }
}
