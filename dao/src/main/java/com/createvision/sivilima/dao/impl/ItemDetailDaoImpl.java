package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ItemDetailDao;
import com.createvision.sivilima.tableModel.ItemDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDetailDao")
public class ItemDetailDaoImpl extends AbstractDaoImpl<ItemDetail, Long> implements ItemDetailDao {
    @Override
    public List<ItemDetail> getItemDetailListByItemId(Long itemId) throws Exception {
        Criteria c= getSession().createCriteria(ItemDetail.class,"itemDetail");
        c.createAlias("itemDetail.item", "item", JoinType.INNER_JOIN);
        c.add(Restrictions.eq("item.id",itemId));
        c.add(Restrictions.eq("isDelete",false));
        return c.list();
    }
}
