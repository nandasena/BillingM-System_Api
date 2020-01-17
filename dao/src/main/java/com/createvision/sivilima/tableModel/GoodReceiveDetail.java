package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "good_receive_detail")
public class GoodReceiveDetail extends BaseObject implements Serializable {

    @Basic
    @Column(name = "description")
    private String  description;

    @Basic
    @Column(name = "received_qty",columnDefinition = "double default 0")
    private double  receivedQTY;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item_id")
    private Item item;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_good_receive_id")
    private GoodReceived goodReceived;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getReceivedQTY() { return receivedQTY; }
    public void setReceivedQTY(double receivedQTY) { this.receivedQTY = receivedQTY; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public GoodReceived getGoodReceived() { return goodReceived; }
    public void setGoodReceived(GoodReceived goodReceived) { this.goodReceived = goodReceived; }
}
