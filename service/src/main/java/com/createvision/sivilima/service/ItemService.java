package com.createvision.sivilima.service;

import com.createvision.sivilima.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems() throws Exception;

    Item getItemById(long id) throws Exception;

    void saveItem(Item item) throws Exception;

    void updateItem(long id, Item item) throws  Exception;

    void deleteItem(long id) throws Exception;
}
