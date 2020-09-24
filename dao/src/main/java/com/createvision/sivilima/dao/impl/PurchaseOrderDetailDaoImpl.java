package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PurchaseOrderDetailDao;
import com.createvision.sivilima.tableModel.ItemCode;
import com.createvision.sivilima.tableModel.PurchaseOrderDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("purchaseOrderDetailDao")
public class PurchaseOrderDetailDaoImpl extends AbstractDaoImpl<PurchaseOrderDetail,Long> implements PurchaseOrderDetailDao {
    @Override
    public List<PurchaseOrderDetail> getPurchaseOrderByIdAndItemId(Long itemId, Long purchaseOrderId) throws Exception {
        Criteria IinCri = getSession().createCriteria(PurchaseOrderDetail.class, "purchaseOrderDetails");
        IinCri.createAlias("purchaseOrderDetails.item", "item");
        IinCri.createAlias("purchaseOrderDetails.purchaseOrder", "purchaseOrder");
        IinCri.add(Restrictions.eq("item.id",itemId));
        IinCri.add(Restrictions.eq("purchaseOrder.id",purchaseOrderId));
        IinCri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        IinCri.setMaxResults(1);
        List<PurchaseOrderDetail> list = IinCri.list();
        return list;
    }
}
