package com.createvision.sivilima.dao;
import com.createvision.sivilima.tableModel.PurchaseOrder;

import java.util.Date;
import java.util.List;

public interface PurchaseOrderDao extends AbstractDao<PurchaseOrder, Long> {
    List<Object[]> getAllPurchaseOrder(Date fromDate, Date toDate) throws Exception;
}
