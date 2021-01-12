package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.TempCustomerDao;
import com.createvision.sivilima.tableModel.TempCustomer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("tempCustomerDao")
public class TempCustomerDaoImpl extends AbstractDaoImpl<TempCustomer, Long> implements TempCustomerDao {

    @Override
    public TempCustomer getCustomerDetailsById(Long id ) throws Exception {

        Session session = getSession();
        Criteria criteria = session.createCriteria(TempCustomer.class, "customer");
        criteria.createAlias("customer.invoice","invoice");
        criteria.add(Restrictions.eq("invoice.id",id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setMaxResults(1);
        TempCustomer result = (TempCustomer) criteria.uniqueResult();
        return result;

    }
}
