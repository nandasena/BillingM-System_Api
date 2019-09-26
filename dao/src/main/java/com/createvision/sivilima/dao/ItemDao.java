package com.createvision.sivilima.dao;

import com.createvision.sivilima.TableModel.Item;

import java.util.List;


public interface ItemDao extends AbstractDao<Item, Long> {

    List<Item> getAllItem()throws Exception;
}
