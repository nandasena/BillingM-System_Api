package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.IPurchaseOrderService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.PurchaseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("purchaseOrderService")
@Transactional
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

    @Autowired
    PurchaseOrderDao purchaseOrderDao;

    @Autowired
    CommonFunctionsImpl commonFunction;

    @Autowired
    SupplierDao supplierDao;

    @Autowired
    IUserDao userDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    PurchaseOrderDetailDao purchaseOrderDetailDao;

    @Override
    public PurchaseOrderVO createPurchaseOrder(PurchaseOrderVO purchaseOrderVO) throws Exception {
        try {

            Supplier supplier = supplierDao.get(purchaseOrderVO.getSupplierId());
            User user = userDao.get(purchaseOrderVO.getUserId());
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setDescription(purchaseOrderVO.getDescription());
            purchaseOrder.setCreatedAt(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            purchaseOrder.setEstimateReceiveDate(commonFunction.getDateTimeByDateString(purchaseOrderVO.getEstimationDate()));
            purchaseOrder.setSupplier(supplier);
            purchaseOrder.setUser(user);
            Long id = purchaseOrderDao.save(purchaseOrder);
            PurchaseOrder insertedPurchaseOrder = purchaseOrderDao.get(id);

            double totalAmount = 0;
            double totalDiscount = 0;
            List<ItemVO> itemVOList = purchaseOrderVO.getItemList();
            for (ItemVO itemVO : itemVOList) {
                PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
                Item item = itemDao.get(itemVO.getItemId());
                double itemTotal = itemVO.getPrice() * itemVO.getPurchaseQuantity();
                double discountTotal = itemVO.getDiscountPercentage() * itemVO.getPurchaseQuantity();
                purchaseOrderDetail.setTotal(itemTotal);
                purchaseOrderDetail.setPrice(itemVO.getPrice());
                purchaseOrderDetail.setQty(itemVO.getPurchaseQuantity());
                purchaseOrderDetail.setItem(item);
                purchaseOrderDetailDao.save(purchaseOrderDetail);
                totalAmount += itemTotal;
                totalDiscount+=discountTotal;
            }
            insertedPurchaseOrder.setTotalAmount(totalAmount);
            insertedPurchaseOrder.setTotalDiscount(totalDiscount);


        } catch (Exception e) {

        }
        return null;
    }
}
