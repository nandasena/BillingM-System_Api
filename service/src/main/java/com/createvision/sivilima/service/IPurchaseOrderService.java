package com.createvision.sivilima.service;

import com.createvision.sivilima.tableModel.GoodReceived;
import com.createvision.sivilima.valuesObject.*;

import java.util.List;

public interface IPurchaseOrderService {

    PurchaseOrderVO createPurchaseOrder(PurchaseOrderVO purchaseOrderVO) throws Exception;
    List <PurchaseOrderVO> getAllPurchaseOrder(String fromDate, String toDate) throws Exception;
    List<ItemDetailsVO> getPurchaseOrderDetailById(Long id)throws Exception;
    List<BranchVO> getBranch()throws Exception;
    List<PurchaseOrderVO> getPurchaseOrderIds()throws Exception;
    GoodReceivedVO saveGoodReceived(GoodReceivedVO goodReceivedVO)throws Exception;
}
