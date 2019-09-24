package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.ItemDetailsVO;

import java.util.List;

public interface ItemDetailService {

    List<ItemDetailsVO> getItemDetailByItemId(Long id) throws Exception;

    ItemDetailsVO createItemDetail(ItemDetailsVO itemDetailsVO) throws Exception;
}
