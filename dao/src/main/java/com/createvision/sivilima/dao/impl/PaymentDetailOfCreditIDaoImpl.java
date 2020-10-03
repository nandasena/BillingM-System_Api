package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PaymentDetailsOfCreditDao;
import com.createvision.sivilima.tableModel.PaymentDetailsOfCredit;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentDetailsOfCreditDao")
public class PaymentDetailOfCreditIDaoImpl extends AbstractDaoImpl<PaymentDetailsOfCredit,Long> implements PaymentDetailsOfCreditDao {
    @Override
    public List getPaymentDetailsById(Long id) throws Exception {
        Session session = getSession();
        Criteria criteria = session.createCriteria(PaymentDetailsOfCredit.class,"paymentDetailsOfCredit");
        criteria.createAlias("paymentDetailsOfCredit.paymentDetails","paymentDetails");
        criteria.add(Restrictions.eq("paymentDetails.id",id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<PaymentDetailsOfCredit> result = criteria.list();
        return result;
    }
}
