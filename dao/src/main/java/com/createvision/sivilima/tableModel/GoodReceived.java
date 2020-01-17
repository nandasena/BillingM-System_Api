package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "good_receive")
public class GoodReceived extends BaseObject implements Serializable {


    @Basic
    @Column(name = "description")
    private String  description;

    @Basic
    @Column(name = "received_date")
    private Date receivedDate;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getReceivedDate() { return receivedDate; }
    public void setReceivedDate(Date receivedDate) { this.receivedDate = receivedDate; }

    public PurchaseOrder getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) { this.purchaseOrder = purchaseOrder; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
