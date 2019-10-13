package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.CategoryDao;
import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.tableModel.SubCategory;
import com.createvision.sivilima.tableModel.Item;
import com.createvision.sivilima.tableModel.ItemDetail;
import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.CategoryVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CommonFunctionsImpl commonFunctions;

    @Override
    public List<ItemVO> getAllItems() throws Exception {
        List<ItemVO> itemVOList = new ArrayList<>();
        try {
            List<Item> itemList = itemDao.getAll();
            for (Item temp : itemList) {
                Set<ItemDetail> itemDetail = new HashSet<>();
                itemDetail = temp.getItemDetails();
                ItemVO itemVO = new ItemVO();
                Set<ItemDetailsVO> itemDetailList = new HashSet<>();
                itemVO.setDescription(temp.getDescription());
                itemVO.setItemName(temp.getName());
                itemVO.setItemId(temp.getId());
                itemVO.setSubCategoryId(temp.getSubCategory().getId());
                itemVO.setItemCode(temp.getItemCode());

                for (ItemDetail temItem : itemDetail) {
                    if(!temItem.isDelete()){
                        ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                        itemDetailsVO.setItemDetailId(temItem.getId());
                        itemDetailsVO.setAvailableQuantity(temItem.getAvailableQuantity());
                        itemDetailsVO.setFabricatorPrice(temItem.getFabricatorPrice());
                        itemDetailsVO.setCustomerPrice(temItem.getCustomerPrice());
                        itemDetailsVO.setMrpPrice(temItem.getMrpPrice());
                        itemDetailsVO.setCostPrice(temItem.getCostPrice());
                        itemDetailsVO.setQuantity(temItem.getQuantity());
                        itemDetailsVO.setDelete(temItem.isDelete());


                        itemDetailList.add(itemDetailsVO);
                    }

                }
                itemVO.setItemDetailList(itemDetailList);
                itemVOList.add(itemVO);

            }
        } catch (Exception e) {
            throw e;
        }
        return itemVOList;
    }

    @Override
    public Item getItemById(long id) throws Exception {
        return itemDao.get(id);
    }

    @Override
    public ItemVO createNewItem(ItemVO itemVO) throws Exception {
        try {
            Date date = commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo");
            Item item = new Item();
            SubCategory subCategory = categoryDao.get(itemVO.getSubCategoryId());
            item.setSubCategory(subCategory);
            item.setCreateDate(date);
            item.setDescription(itemVO.getDescription());
            item.setName(itemVO.getItemName());
            Long createItemId = itemDao.save(item);
            itemVO.setItemId(createItemId);

        } catch (Exception e) {
            throw e;
        }
        return itemVO;
    }

    @Override
    public void updateItem(long id, Item item) throws Exception {
        Item itemDB = getItemById(id);
        //set new item details

        itemDao.update(itemDB);

    }

    @Override
    public void deleteItem(long id) throws Exception {
        Item item = getItemById(id);

        itemDao.delete(item);
    }

    @Override
    public List<CategoryVO> getAllCategory() throws Exception {
        List<SubCategory> subCategoryList = categoryDao.getAll();
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (SubCategory tempSubCategory : subCategoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryId(tempSubCategory.getId());
            categoryVO.setName(tempSubCategory.getName());
            categoryVOList.add(categoryVO);
        }
        return categoryVOList;
    }
}
