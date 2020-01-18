package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.BranchVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.PurchaseOrderVO;

import java.util.List;

public interface IPurchaseOrderService {

    PurchaseOrderVO createPurchaseOrder(PurchaseOrderVO purchaseOrderVO) throws Exception;
    List <PurchaseOrderVO> getAllPurchaseOrder(String fromDate, String toDate) throws Exception;
    List<ItemDetailsVO> getPurchaseOrderDetailById(Long id)throws Exception;
    List<BranchVO> getBranch()throws Exception;
}
