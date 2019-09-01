package com.createvision.sivilima.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item  extends BaseObject implements Serializable {

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "created_date")
    private Date createdDate;

    @Basic
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private Set<InvoiceItemDetail> invoiceItemDetails = new HashSet<InvoiceItemDetail>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private Set<ItemDetail> itemDetails = new HashSet<ItemDetail>(0);

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getCreateDate() { return createdDate; }
    public void setCreateDate(Date createDate) { this.createdDate = createDate; }

    public Date getLastUpdateDate() { return lastUpdatedDate; }
    public void setLastUpdateDate(Date lastUpdateDate) { this.lastUpdatedDate = lastUpdateDate; }

    public Set<InvoiceItemDetail> getInvoiceItemDetails() { return invoiceItemDetails; }
    public void setInvoiceItemDetails(Set<InvoiceItemDetail> invoiceItemDetails) { this.invoiceItemDetails = invoiceItemDetails; }

    public Set<ItemDetail> getItemDetails() { return itemDetails; }
    public void setItemDetails(Set<ItemDetail> itemDetails) { this.itemDetails = itemDetails; }
}
