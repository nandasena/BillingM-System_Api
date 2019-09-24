package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ItemDetailDao;
import com.createvision.sivilima.TableModel.ItemDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDetailDao")
public class ItemDetailDaoImpl extends AbstractDaoImpl<ItemDetail, Long> implements ItemDetailDao {
    @Override
    public List<ItemDetail> getItemDetailListByItemId(Long itemId) throws Exception {
        Criteria c= getSession().createCriteria(ItemDetail.class,"itemDetail");
        c.createAlias("itemDetail.item", "item");
        c.add(Restrictions.eq("item.id",itemId));
        return c.list();
    }
}
