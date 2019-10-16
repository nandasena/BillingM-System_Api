package com.createvision.sivilima.service;

import com.createvision.sivilima.tableModel.Item;
import com.createvision.sivilima.valuesObject.CategoryVO;
import com.createvision.sivilima.valuesObject.ItemVO;

import java.util.List;

public interface ItemService {

    List<ItemVO> getAllItems() throws Exception;

    Item getItemById(long id) throws Exception;

    ItemVO createNewItem(ItemVO itemVO) throws Exception;

    void updateItem(long id, Item item) throws  Exception;

    void deleteItem(long id) throws Exception;

    List<CategoryVO> getAllSubCategory() throws Exception;

    List<CategoryVO> getAllMainCategory() throws Exception;

    List<CategoryVO> createMainCategory(List<CategoryVO> categoryVOS)throws Exception;


}
