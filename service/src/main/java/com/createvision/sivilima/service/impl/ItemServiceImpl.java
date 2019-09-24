package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.CategoryDao;
import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.TableModel.Category;
import com.createvision.sivilima.TableModel.Item;
import com.createvision.sivilima.TableModel.ItemDetail;
import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.CategoryVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.service.impl.CommonFunctionsImpl;
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
                itemVO.setCategoryId(temp.getCategory().getId());

                for (ItemDetail temItem : itemDetail) {
                    ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                    itemDetailsVO.setItemDetailId(temItem.getId());
                    itemDetailsVO.setAvailableQuantity(temItem.getAvailableQuantity());
                    itemDetailsVO.setSellingPrice(temItem.getPrice());
                    itemDetailsVO.setCostPrice(temItem.getCostPrice());
                    itemDetailsVO.setQuantity(temItem.getQuantity());

                    itemDetailList.add(itemDetailsVO);
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
            Date date = commonFunctions.getCurrentTime("Asia/Colombo");
            Item item = new Item();
            Category category = categoryDao.get(itemVO.getCategoryId());
            item.setCategory(category);
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
        List<Category> categoryList = categoryDao.getAll();
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category tempCategory : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryId(tempCategory.getId());
            categoryVO.setName(tempCategory.getName());
            categoryVOList.add(categoryVO);
        }
        return categoryVOList;
    }
}
