package com.createvision.sivilima.valuesObject;

import com.createvision.sivilima.model.ItemDetail;

import java.util.List;
import java.util.Set;

public class ItemVO {
    Long id;
    Long itemId;
    String itemName;
    String description;
    Set<ItemDetailsVO> itemDetailList;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id;}

    public Long getItemId() {return itemId;}
    public void setItemId(Long itemId) {this.itemId = itemId;}

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<ItemDetailsVO> getItemDetailList() { return itemDetailList; }
    public void setItemDetailList(Set<ItemDetailsVO> itemDetailList) { this.itemDetailList = itemDetailList; }
}
