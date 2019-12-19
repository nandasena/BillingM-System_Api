package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PurchaseOrderDao;
import com.createvision.sivilima.tableModel.PurchaseOrder;
import org.springframework.stereotype.Repository;

@Repository("purchaseOrderDao")
public class PurchaseOrderDaoImpl  extends AbstractDaoImpl<PurchaseOrder,Long> implements PurchaseOrderDao {
}
