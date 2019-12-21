package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PurchaseOrderDao;
import com.createvision.sivilima.tableModel.PurchaseOrder;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("purchaseOrderDao")
public class PurchaseOrderDaoImpl  extends AbstractDaoImpl<PurchaseOrder,Long> implements PurchaseOrderDao {
    @Override
    public List<Object[]> getAllPurchaseOrder(Date fromDate, Date toDate) throws Exception {
        Query query = getSession().createSQLQuery("CALL getPurchaseOrderByDate(?,?)");
        query.setParameter(0,fromDate);
        query.setParameter(1,toDate);
        List<Object[]> result = query.list();
        return result;

    }
}
