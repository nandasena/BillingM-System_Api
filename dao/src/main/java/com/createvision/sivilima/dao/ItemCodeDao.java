package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.ItemCode;

public interface ItemCodeDao extends AbstractDao<ItemCode, Long> {
    ItemCode getItemCode (String code) throws Exception;
}
