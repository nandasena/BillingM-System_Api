package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PaymentMethodDao;
import com.createvision.sivilima.tableModel.PaymentMethod;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("paymentMethodDao")
public class PaymentMethodDaoImpl extends AbstractDaoImpl<PaymentMethod,Long> implements PaymentMethodDao {

    @Override
    public PaymentMethod getPaymentMethodByTypeCode(String code) throws Exception {
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(PaymentMethod.class, "paymentMethod");
            criteria.add(Restrictions.eq("paymentMethod.typeCode",code));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(1);
            PaymentMethod result = (PaymentMethod) criteria.uniqueResult();
            return result;
        }catch (Exception e){
            throw e;
        }
    }
}
