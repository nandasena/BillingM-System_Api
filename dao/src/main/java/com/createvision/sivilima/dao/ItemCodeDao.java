package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.ItemCode;

import java.util.List;

public interface ItemCodeDao extends AbstractDao<ItemCode, Long> {
    List<ItemCode> getItemCode (String code) throws Exception;
}
