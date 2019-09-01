package com.createvision.sivilima.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoice_item_Details")
public class InvoiceItemDetail extends BaseObject implements Serializable {

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public Item getItem() { return item; }
    public void setItem(Item item) {this.item = item; }
}
