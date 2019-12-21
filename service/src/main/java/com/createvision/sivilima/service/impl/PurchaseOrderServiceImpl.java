package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.IPurchaseOrderService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;
import com.createvision.sivilima.valuesObject.PurchaseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

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

    @Autowired
    ItemCodeDao itemCodeDao;

    @Override
    public PurchaseOrderVO createPurchaseOrder(PurchaseOrderVO purchaseOrderVO) throws Exception {
        PurchaseOrderVO insertedOrder = purchaseOrderVO;
        try {
            List<ItemCode> itemCodeList = itemCodeDao.getItemCode("PURCHASE");
            ItemCode itemCode = itemCodeList.get(itemCodeList.size() - 1);
            String code = itemCode.getCode();
            int lastNUmber = itemCode.getNextNumber();
            String lastPurchaseCode = new Integer(itemCode.getNextNumber()).toString();
            String purchaseCode = code + "-" + lastPurchaseCode;
            itemCode.setNextNumber(++lastNUmber);
            itemCodeDao.save(itemCode);

            Supplier supplier = supplierDao.get(purchaseOrderVO.getSupplierId());
            User user = userDao.get(purchaseOrderVO.getUserId());
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setDescription(purchaseOrderVO.getDescription());
            purchaseOrder.setCreatedAt(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            purchaseOrder.setEstimateReceiveDate(commonFunction.getDateTimeByDateString(purchaseOrderVO.getEstimationDate()));
            purchaseOrder.setSupplier(supplier);
            purchaseOrder.setUser(user);
            purchaseOrder.setPurchaseCode(purchaseCode);
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
                totalDiscount += discountTotal;
            }
            insertedPurchaseOrder.setTotalAmount(totalAmount);
            insertedPurchaseOrder.setTotalDiscount(totalDiscount);
            purchaseOrderDao.save(insertedPurchaseOrder);

            insertedOrder.setAddress1(supplier.getAddress1());
            insertedOrder.setAddress2(supplier.getAddress2());
            insertedOrder.setSupplierName(supplier.getName());
            insertedOrder.setPurchaseCode(purchaseCode);

        } catch (Exception e) {
            throw e;
        }
        return insertedOrder;
    }

    @Override
    public List<PurchaseOrderVO> getAllPurchaseOrder(String fromDate,String toDate) throws Exception {
        List<PurchaseOrderVO> purchaseOrderVOList = new ArrayList<>();
        try {
           // List<PurchaseOrder> purchaseOrderList = purchaseOrderList.getAll();
            List<Object[]> purchaseOrderList =purchaseOrderDao.getAllPurchaseOrder(commonFunction.getDateTimeByDateString(fromDate),commonFunction.getDateTimeByDateString(toDate));

            for (Object[] purchaseOrder : purchaseOrderList) {
                PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();

                purchaseOrderVO.setPurchaseCode(purchaseOrder[1].toString());
                purchaseOrderVO.setSupplierName(purchaseOrder[2].toString());
                purchaseOrderVO.setAddress1(purchaseOrder[3].toString());
                purchaseOrderVO.setAddress2(purchaseOrder[4].toString());
                purchaseOrderVO.setUserName(purchaseOrder[5].toString());
                purchaseOrderVO.setEstimationDate(purchaseOrder[6].toString());
                purchaseOrderVO.setTotalAmount(parseDouble(purchaseOrder[7].toString()));
                purchaseOrderVO.setTotalDiscount(parseDouble(purchaseOrder[8].toString()));
                purchaseOrderVO.setUserId(Long.parseLong(purchaseOrder[9].toString()));
                purchaseOrderVO.setSupplierId(Long.parseLong(purchaseOrder[9].toString()));

                purchaseOrderVOList.add(purchaseOrderVO);

            }
        } catch (Exception e) {
            throw e;
        }
        return purchaseOrderVOList;
    }
}
