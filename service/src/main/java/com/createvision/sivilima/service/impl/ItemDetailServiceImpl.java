package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.TableModel.ItemDetail;
import com.createvision.sivilima.dao.ItemDetailDao;
import com.createvision.sivilima.service.ItemDetailService;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("itemDetailService")
@Transactional
public class ItemDetailServiceImpl implements ItemDetailService {

    @Autowired
    ItemDetailDao itemDetailDao;

    @Override
    public List<ItemDetailsVO> getItemDetailByItemId(Long id) throws Exception {
        List<ItemDetailsVO> itemDetailsVOList = new ArrayList<>();
        try {

            List<ItemDetail> itemDetailList = itemDetailDao.getItemDetailListByItemId(id);
            for (ItemDetail tempItemDetail : itemDetailList) {
                ItemDetailsVO itemDetail = new ItemDetailsVO();

                itemDetail.setAvailableQuantity(tempItemDetail.getAvailableQuantity());
                itemDetail.setQuantity(tempItemDetail.getQuantity());
                itemDetail.setCostPrice(tempItemDetail.getCostPrice());
                itemDetail.setSellingPrice(tempItemDetail.getPrice());
                itemDetail.setItemDetailId(tempItemDetail.getId());
                itemDetailsVOList.add(itemDetail);
            }
        } catch (Exception e) {
            throw e;
        }

        return itemDetailsVOList;
    }
}
