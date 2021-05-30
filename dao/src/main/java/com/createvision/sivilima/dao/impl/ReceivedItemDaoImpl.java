package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ReceivedItemDao;
import com.createvision.sivilima.tableModel.ItemReceived;
import org.springframework.stereotype.Repository;

@Repository("receivedItemDaoImpl")
public class ReceivedItemDaoImpl extends AbstractDaoImpl<ItemReceived,Long> implements ReceivedItemDao {

}
