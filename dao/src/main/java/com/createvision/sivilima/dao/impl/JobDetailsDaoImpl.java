package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.JobDetailsDao;
import com.createvision.sivilima.tableModel.JobDetails;
import org.springframework.stereotype.Repository;

@Repository("jobDetailsDaoImpl")
public class JobDetailsDaoImpl  extends AbstractDaoImpl<JobDetails,Long>implements JobDetailsDao {
}
