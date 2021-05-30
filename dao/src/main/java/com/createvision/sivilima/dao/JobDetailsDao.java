package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.JobDetails;

import java.util.List;

public interface JobDetailsDao extends AbstractDao<JobDetails,Long>{
    List<JobDetails> getItemListByItemDetailIdAndJobId(Long jobId, Long itemDetailsId)throws Exception;
}
