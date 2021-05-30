package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.JobDetailsDao;
import com.createvision.sivilima.tableModel.JobDetails;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jobDetailsDaoImpl")
public class JobDetailsDaoImpl  extends AbstractDaoImpl<JobDetails,Long>implements JobDetailsDao {
    @Override
    public List<JobDetails> getItemListByItemDetailIdAndJobId(Long jobId, Long itemDetailsId) throws Exception {
        Criteria IinCri = getSession().createCriteria(JobDetails.class, "jobDetails");
        IinCri.createAlias("jobDetails.job", "job");
        IinCri.createAlias("jobDetails.itemDetail", "itemDetail" );
        IinCri.add(Restrictions.eq("job.id",jobId));
        IinCri.add(Restrictions.eq("itemDetail.id",itemDetailsId));
        IinCri.addOrder(Order.desc("jobDetails.createdAt"));
        IinCri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<JobDetails> itemReceived = IinCri.list();
        return itemReceived;
    }
}
