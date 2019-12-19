package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PurchaseOrderDetailDao;
import com.createvision.sivilima.tableModel.PurchaseOrderDetail;
import org.springframework.stereotype.Repository;

@Repository("purchaseOrderDetailDao")
public class PurchaseOrderDetailDaoImpl extends AbstractDaoImpl<PurchaseOrderDetail,Long> implements PurchaseOrderDetailDao {
}
