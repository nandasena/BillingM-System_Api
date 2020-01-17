package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.DebtorDao;
import com.createvision.sivilima.tableModel.Debtor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("debtorDao")
public class DebtorDaoImpl extends AbstractDaoImpl<Debtor, Long> implements DebtorDao {

    @Override
    public List<Debtor> getDebtorByCustomerId(Long id) throws Exception {
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Debtor.class, "debtor");
            criteria.createAlias("debtor.customer", "customer", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.eq("customer.id", id));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Debtor> result = criteria.list();
            return result;
        }catch (Exception e){
            throw e;
        }

    }
}
