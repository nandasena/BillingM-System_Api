package com.createvision.sivilima.valuesObject;

import com.createvision.sivilima.tableModel.Invoice;
import com.createvision.sivilima.tableModel.Item;

public class InvoiceItemDetailsVO {

    private Item item;
    private Invoice invoice;

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

}
