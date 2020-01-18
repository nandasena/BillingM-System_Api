package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.IPurchaseOrderService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.BranchVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.PurchaseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Autowired
    BranchDao branchDao;

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
                double itemTotal = itemVO.getPrice() * itemVO.getOrderQuantity();
                double discountTotal = itemVO.getDiscountPercentage() * itemVO.getOrderQuantity();
                purchaseOrderDetail.setTotal(itemTotal);
                purchaseOrderDetail.setPrice(itemVO.getPrice());
                purchaseOrderDetail.setQty(itemVO.getOrderQuantity());
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
            insertedOrder.setSupplierName(supplier.getFirstName());
            insertedOrder.setPurchaseCode(purchaseCode);

        } catch (Exception e) {
            throw e;
        }
        return insertedOrder;
    }

    @Override
    public List<PurchaseOrderVO> getAllPurchaseOrder(String fromDate, String toDate) throws Exception {
        List<PurchaseOrderVO> purchaseOrderVOList = new ArrayList<>();
        try {
            List<Object[]> purchaseOrderList = purchaseOrderDao.getAllPurchaseOrder(commonFunction.getDateTimeByDateString(fromDate), commonFunction.getDateTimeByDateString(toDate));

            for (Object[] purchaseOrder : purchaseOrderList) {
                PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();

                purchaseOrderVO.setPurchaseOrderId(Long.parseLong(purchaseOrder[0].toString()));
                purchaseOrderVO.setPurchaseCode(purchaseOrder[1].toString());
                purchaseOrderVO.setSupplierName(purchaseOrder[2].toString());
                purchaseOrderVO.setAddress1(purchaseOrder[3].toString());
                purchaseOrderVO.setAddress2(purchaseOrder[4].toString());
                purchaseOrderVO.setUserName(purchaseOrder[5].toString());
                purchaseOrderVO.setEstimationDate(purchaseOrder[6].toString());
                purchaseOrderVO.setTotalAmount(parseDouble(purchaseOrder[7].toString()));
                purchaseOrderVO.setTotalDiscount(parseDouble(purchaseOrder[8].toString()));
                purchaseOrderVO.setUserId(Long.parseLong(purchaseOrder[9].toString()));
                purchaseOrderVO.setSupplierCode(purchaseOrder[10].toString());

                purchaseOrderVOList.add(purchaseOrderVO);

            }
        } catch (Exception e) {
            throw e;
        }
        return purchaseOrderVOList;
    }

    @Override
    public List<ItemDetailsVO> getPurchaseOrderDetailById(Long id) throws Exception {
        List<ItemDetailsVO> itemDetailsVOList = new ArrayList<>();
        try {
            PurchaseOrder purchaseOrderList = purchaseOrderDao.get(id);
            List<PurchaseOrderDetail> purchaseOrderDetailList = purchaseOrderList.getPurchaseOrderDetails();

            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetailList) {
                ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                itemDetailsVO.setCostPrice(purchaseOrderDetail.getPrice());
                itemDetailsVO.setQuantity(purchaseOrderDetail.getQty());
                itemDetailsVO.setTotalItemAmount(purchaseOrderDetail.getTotal());
                itemDetailsVO.setTotalItemDiscount(purchaseOrderDetail.getTotalDiscount());
                itemDetailsVO.setReceivedQuantity(purchaseOrderDetail.getReceivedQTY());
                itemDetailsVO.setItemName(purchaseOrderDetail.getItem().getName());
                itemDetailsVO.setItemId(purchaseOrderDetail.getItem().getId());

                itemDetailsVOList.add(itemDetailsVO);
            }

        } catch (Exception e) {
            throw e;
        }
        return itemDetailsVOList;
    }

    @Override
    public List<BranchVO> getBranch() throws Exception {
        try {
            List<BranchVO> branchVOList = new ArrayList<>();
            List<Branch> branchList = branchDao.getAll();
            if (!branchList.isEmpty()) {
                for (Branch b : branchList) {

                    BranchVO branchVO = new BranchVO();
                    branchVO.setId(b.getId());
                    branchVO.setName(b.getName());

                    branchVOList.add(branchVO);
                }
            }
            return branchVOList;
        } catch (Exception e) {
            throw e;

        }

    }
}
