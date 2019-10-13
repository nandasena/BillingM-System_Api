package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.tableModel.CompanyDetail;
import com.createvision.sivilima.tableModel.Item;
import com.createvision.sivilima.tableModel.ItemDetail;
import com.createvision.sivilima.dao.CompanyDao;
import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.dao.ItemDetailDao;
import com.createvision.sivilima.service.ItemDetailService;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("itemDetailService")
@Transactional
public class ItemDetailServiceImpl implements ItemDetailService {

    @Autowired
    ItemDetailDao itemDetailDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    CommonFunctionsImpl commonFunctions;

    @Override
    public List<ItemDetailsVO> getItemDetailByItemId(Long id) throws Exception {
        List<ItemDetailsVO> itemDetailsVOList = new ArrayList<>();
        try {

            List<ItemDetail> itemDetailList = itemDetailDao.getItemDetailListByItemId(id);
            for (ItemDetail tempItemDetail : itemDetailList) {
                ItemDetailsVO itemDetailVO = new ItemDetailsVO();

                itemDetailVO.setAvailableQuantity(tempItemDetail.getAvailableQuantity());
                itemDetailVO.setQuantity(tempItemDetail.getQuantity());
                itemDetailVO.setCostPrice(tempItemDetail.getCostPrice());
                itemDetailVO.setSellingPrice(tempItemDetail.getFabricatorPrice());
                itemDetailVO.setItemDetailId(tempItemDetail.getId());
                itemDetailVO.setDelete(tempItemDetail.isDelete());

                itemDetailsVOList.add(itemDetailVO);


            }
        } catch (Exception e) {
            throw e;
        }

        return itemDetailsVOList;
    }

    @Override
    public ItemDetailsVO createItemDetail(ItemDetailsVO itemDetailsVO) throws Exception {

        try {
            Item item = itemDao.get(itemDetailsVO.getItemId());
            CompanyDetail companyDetail = companyDao.get(itemDetailsVO.getCompanyId());
            Date createdDate = commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo");
            Date purchaseDate = commonFunctions.getDateTimeByDateString(itemDetailsVO.getPurchaseDate());

            ItemDetail itemDetail = new ItemDetail();
            itemDetail.setItem(item);
            itemDetail.setCompanyDetail(companyDetail);
            itemDetail.setCreatedAt(createdDate);
            itemDetail.setPurchaseDate(purchaseDate);
            itemDetail.setQuantity(itemDetailsVO.getQuantity());
            itemDetail.setAvailableQuantity(itemDetailsVO.getAvailableQuantity());
            itemDetail.setCostPrice(itemDetailsVO.getCostPrice());
            itemDetail.setFabricatorPrice(itemDetailsVO.getSellingPrice());

            Long id = itemDetailDao.save(itemDetail);

            itemDetailsVO.setItemDetailId(id);

        } catch (Exception e) {
            throw e;
        }
        return itemDetailsVO;
    }

    @Override
    public boolean deleteItemDetail(Long id) throws Exception {
        try {
            ItemDetail itemDetail = itemDetailDao.get(id);
            itemDetail.setDelete(true);
            Long returnId = itemDetailDao.save(itemDetail);
            if (returnId != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
