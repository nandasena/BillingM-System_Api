package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.PurchaseOrderVO;

import java.util.List;

public interface IPurchaseOrderService {

    PurchaseOrderVO createPurchaseOrder(PurchaseOrderVO purchaseOrderVO) throws Exception;
    List <PurchaseOrderVO> getAllPurchaseOrder() throws Exception;
}
