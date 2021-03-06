package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.tableModel.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDaoImpl<Item, Long> implements ItemDao {

    @Override
    public List<Item> getAllItem() throws Exception {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Item.class, "item");
        criteria.createAlias("item.itemDetails", "itemDetails", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("itemDetails.isDelete", false));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Item> result = criteria.list();
        return result;
    }

    @Override
    public Item getItemByItemCode(String itemCode) throws Exception {
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Item.class, "item");
            criteria.add(Restrictions.eq("item.itemCode",itemCode));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(1);
            Item result = (Item) criteria.uniqueResult();
            return result;
        }catch (Exception e){
            throw e;
        }

    }
}
