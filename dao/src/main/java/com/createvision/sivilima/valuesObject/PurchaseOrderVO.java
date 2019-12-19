package com.createvision.sivilima.valuesObject;

import java.util.List;

public class PurchaseOrderVO {
    private String description;
    private String estimationDate;
    private Long supplierId;
    private Long userId;
    private List<ItemVO> ItemList;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEstimationDate() { return estimationDate; }
    public void setEstimationDate(String estimationDate) { this.estimationDate = estimationDate; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<ItemVO> getItemList() { return ItemList; }
    public void setItemList(List<ItemVO> itemList) { ItemList = itemList; }


}
