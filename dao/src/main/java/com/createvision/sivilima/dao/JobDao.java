package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.Job;

import java.util.Date;
import java.util.List;

public interface JobDao extends AbstractDao<Job, Long> {
    List<Job>getJobList()throws Exception;
    List<Object[]> getJobByDateRange(Date fromDate, Date toDate) throws Exception;
}
