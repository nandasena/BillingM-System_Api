package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.model.Item;
import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDaoImpl<Item, Long> implements ItemDao {

}
