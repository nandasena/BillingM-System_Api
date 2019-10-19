package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.Item;

import java.util.List;


public interface ItemDao extends AbstractDao<Item, Long> {

    List<Item> getAllItem()throws Exception;
    Item getItemByItemCode(String itemCode)throws Exception;
}
