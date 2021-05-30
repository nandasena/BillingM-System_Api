package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "received_item")
public class ItemReceived extends BaseObject implements Serializable {

    @Basic
    @Column(name = "received_quantity")
    private double receivedQuantity;
    public double getReceivedQuantity() {return receivedQuantity;}
    public void setReceivedQuantity(double receivedQuantity) {this.receivedQuantity = receivedQuantity;}

    @ManyToOne
    @JoinColumn(name = "fk_item_id")
    private Item itemId;
    public Item getItemId() {return itemId;}
    public void setItemId(Item itemId) {this.itemId = itemId;}

    @ManyToOne
    @JoinColumn(name = "fk_item_details_id")
    private ItemDetail itemDetail;
    public ItemDetail getItemDetail() {return itemDetail;}
    public void setItemDetail(ItemDetail itemDetail) {this.itemDetail = itemDetail;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_id")
    private Job jobId;
    public Job getJobId() {return jobId;}
    public void setJobId(Job jobId) {this.jobId = jobId;}
}
