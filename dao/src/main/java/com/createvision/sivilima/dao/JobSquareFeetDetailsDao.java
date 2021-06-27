package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.JobSquareFeetDetails;

public interface JobSquareFeetDetailsDao extends AbstractDao<JobSquareFeetDetails,Long> {
    public void delete(Long jobId) throws Exception;
}
