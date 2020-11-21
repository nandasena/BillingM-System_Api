package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.CreditorDao;
import com.createvision.sivilima.tableModel.Creditor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("creditorDao")
public class CreditorDaoImpl extends AbstractDaoImpl<Creditor, Long> implements CreditorDao {

    @Override
    public List<Creditor> getSupplierListById(Long id) throws Exception {
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Creditor.class, "creditor");
            criteria.createAlias("creditor.supplier", "supplier", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.eq("supplier.id", id));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Creditor> result = criteria.list();
            return result;
        }catch (Exception e){
            throw e;
        }
    }
}
