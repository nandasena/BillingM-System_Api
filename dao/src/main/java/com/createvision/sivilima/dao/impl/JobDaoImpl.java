package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.JobDao;
import com.createvision.sivilima.tableModel.Job;
import com.createvision.sivilima.tableModel.JobStatus;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("jobDao")
public class JobDaoImpl extends AbstractDaoImpl<Job, Long> implements JobDao {
    public List<Job> getJobList() throws Exception {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Job.class, "job");
        criteria.add(Restrictions.disjunction()
                .add(Restrictions.eq("job.jobStatus", JobStatus.valueOf("CREATE")))
                .add(Restrictions.eq("job.jobStatus", JobStatus.valueOf("START")))
                .add(Restrictions.eq("job.jobStatus", JobStatus.valueOf("PENDING"))));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Job> result = criteria.list();
        return result;
    }

    @Override
    public List<Object[]> getJobByDateRange(Date fromDate, Date toDate) throws Exception {
        Query query = getSession().createSQLQuery("CALL getJobByDateRange(?,?)");
        query.setParameter(0,fromDate);
        query.setParameter(1,toDate);
        List<Object[]> result = query.list();
        return result;
    }
}
