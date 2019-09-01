package com.createvision.sivilims.valueobject;
import com.createvision.sivilima.model.Invoice;
import com.createvision.sivilima.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

public class InvoiceItemDetailsVO {

    private Item item;
    private Invoice invoice;

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public Item getItem() { return item; }
    public void setItem(Item item) {this.item = item; }
}
