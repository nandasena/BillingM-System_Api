package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.QuotationDetailsDao;
import com.createvision.sivilima.tableModel.QuotationDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QuotationDetailDao")
public class QuotationDetailDaoImpl extends AbstractDaoImpl<QuotationDetails,Long> implements QuotationDetailsDao {

    @Override
    public List<QuotationDetails> gteInvoiceDetailByInvoiceId(Long invoiceId) throws Exception {
        Session session = getSession();
        Criteria criteria = session.createCriteria(QuotationDetails.class, "QuotationItemDetail");
        criteria.createAlias("QuotationItemDetail.quotation", "quotation", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("quotation.id", invoiceId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<QuotationDetails> result = criteria.list();
        return result;
    }
}
