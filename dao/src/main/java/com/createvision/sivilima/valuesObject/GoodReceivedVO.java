package com.createvision.sivilima.valuesObject;

import java.util.List;

public class GoodReceivedVO {
    private String receivedDate;
    private Long purchaseOrderId;
    private List<ItemDetailsVO> itemDetailsVOList;


    public String getReceivedDate() { return receivedDate; }
    public void setReceivedDate(String receivedDate) { this.receivedDate = receivedDate; }

    public Long getPurchaseOrderId() { return purchaseOrderId; }
    public void setPurchaseOrderId(Long purchaseOrderId) { this.purchaseOrderId = purchaseOrderId; }

    public List<ItemDetailsVO> getItemDetailsVOList() { return itemDetailsVOList; }
    public void setItemDetailsVOList(List<ItemDetailsVO> itemDetailsVOList) { this.itemDetailsVOList = itemDetailsVOList; }
}
