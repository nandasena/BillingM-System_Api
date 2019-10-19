package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ItemCodeDao;
import com.createvision.sivilima.tableModel.Item;
import com.createvision.sivilima.tableModel.ItemCode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("itemCodeDao")
public class ItemCodeDaoImpl extends AbstractDaoImpl<ItemCode, Long> implements ItemCodeDao {

    @Override
    public ItemCode getItemCode(String code) throws Exception {

        Session session = getSession();
        Criteria criteria = session.createCriteria(ItemCode.class, "itemCode");
        criteria.add(Restrictions.eq("itemCode.codeName",code));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setMaxResults(1);
        ItemCode result = (ItemCode) criteria.uniqueResult();
        return result;

    }
}
