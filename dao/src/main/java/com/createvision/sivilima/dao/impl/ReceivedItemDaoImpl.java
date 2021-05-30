package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ReceivedItemDao;
import com.createvision.sivilima.tableModel.ItemReceived;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("receivedItemDaoImpl")
public class ReceivedItemDaoImpl extends AbstractDaoImpl<ItemReceived,Long> implements ReceivedItemDao {
    @Override
    public List<ItemReceived> getReceivedItemById(Long jobId, Long itemId) throws Exception {
        Criteria IinCri = getSession().createCriteria(ItemReceived.class, "itemReceived");
        IinCri.createAlias("itemReceived.jobId", "job");
        IinCri.createAlias("itemReceived.itemDetail", "itemDetail" );
        IinCri.add(Restrictions.eq("job.id",jobId));
        IinCri.add(Restrictions.eq("itemDetail.id",itemId));
        IinCri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ItemReceived> itemReceived = IinCri.list();

        return itemReceived;


    }
}
