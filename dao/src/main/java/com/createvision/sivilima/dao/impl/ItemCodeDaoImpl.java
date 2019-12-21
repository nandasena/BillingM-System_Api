package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ItemCodeDao;
import com.createvision.sivilima.tableModel.Item;
import com.createvision.sivilima.tableModel.ItemCode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemCodeDao")
public class ItemCodeDaoImpl extends AbstractDaoImpl<ItemCode, Long> implements ItemCodeDao {

    @Override
    public List<ItemCode> getItemCode(String code) throws Exception {

        Session session = getSession();
        Criteria criteria = session.createCriteria(ItemCode.class, "itemCode");
        criteria.add(Restrictions.eq("itemCode.codeName",code));
        criteria.add(Restrictions.eq("itemCode.isDelete",false));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(1);
        criteria.setMaxResults(1);
        List<ItemCode> list = criteria.list();
     //   ItemCode result = (ItemCode) criteria.uniqueResult();
        return list;

    }
}
