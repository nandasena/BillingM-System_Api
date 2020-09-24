package com.createvision.sivilima.dao;
import com.createvision.sivilima.tableModel.PurchaseOrderDetail;

import java.util.List;

public interface PurchaseOrderDetailDao extends AbstractDao<PurchaseOrderDetail, Long> {
    public List<PurchaseOrderDetail> getPurchaseOrderByIdAndItemId(Long itemId, Long purchaseOrderId) throws Exception;

}
