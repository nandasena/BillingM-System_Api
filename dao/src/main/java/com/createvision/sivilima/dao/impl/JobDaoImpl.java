package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.JobDao;
import com.createvision.sivilima.tableModel.Job;
import org.springframework.stereotype.Repository;

@Repository("jobDao")
public class JobDaoImpl extends AbstractDaoImpl<Job, Long> implements JobDao {
}
