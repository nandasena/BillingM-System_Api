package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.model.Item;
import com.createvision.sivilima.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Item> getAllItems() throws Exception {
        return itemDao.getAll();
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
