package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.model.Item;
import com.createvision.sivilima.model.ItemDetail;
import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

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
                itemVO.setId(temp.getId());

                for (ItemDetail temItem : itemDetail) {
                    ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                    itemDetailsVO.setId(temItem.getId());
                    itemDetailsVO.setAvailableQuantity(temItem.getAvailableQuantity());
                    itemDetailsVO.setPrice(temItem.getPrice());
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
    public void saveItem(Item item) throws Exception {
        itemDao.save(item);
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
}
