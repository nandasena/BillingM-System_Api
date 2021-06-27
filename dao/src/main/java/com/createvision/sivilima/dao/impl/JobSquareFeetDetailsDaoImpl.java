package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.JobSquareFeetDetailsDao;
import com.createvision.sivilima.tableModel.JobSquareFeetDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jobSquareFeetDetailsDaoImpl")
public class JobSquareFeetDetailsDaoImpl extends AbstractDaoImpl<JobSquareFeetDetails,Long> implements JobSquareFeetDetailsDao {

    @Override
    public void delete(Long jobId) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(JobSquareFeetDetails.class, "JobSquareFeetDetails");
        criteria.createAlias("JobSquareFeetDetails.job", "job");
        criteria.add(Restrictions.eq("job.id",jobId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<JobSquareFeetDetails> result = criteria.list();
        for (JobSquareFeetDetails js:result) {
            session.delete(js);
        }

    }
}
