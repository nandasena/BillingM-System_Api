package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.InvoiceItemDetailDao;
import com.createvision.sivilima.tableModel.InvoiceItemDetail;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("invoiceItemDetailDao")
public class InvoiceItemDetailDaoImpl extends AbstractDaoImpl<InvoiceItemDetail, Long> implements InvoiceItemDetailDao {

    @Override
    public List<InvoiceItemDetail> gteInvoiceDetailByInvoiceId(Long invoiceId) throws Exception {
        Session session = getSession();
        Criteria criteria = session.createCriteria(InvoiceItemDetail.class, "InvoiceItemDetail");
        criteria.createAlias("InvoiceItemDetail.invoice", "invoice", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("invoice.id", invoiceId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<InvoiceItemDetail> result = criteria.list();
        return result;
    }
}
