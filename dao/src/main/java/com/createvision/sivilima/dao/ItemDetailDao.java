package com.createvision.sivilima.dao;

import com.createvision.sivilima.TableModel.ItemDetail;

import java.util.List;

public interface ItemDetailDao extends AbstractDao<ItemDetail, Long> {

    List<ItemDetail> getItemDetailListByItemId(Long itemId)throws Exception;
}
