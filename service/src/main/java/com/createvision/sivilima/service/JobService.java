package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.JobVO;
import com.createvision.sivilima.valuesObject.OtherExpensesVO;
import java.util.List;

public interface JobService {
    public List<OtherExpensesVO> getAllOtherExpenses() throws Exception;
    public JobVO createJob(JobVO jobVO) throws Exception;
    public List<JobVO>getJobList()throws Exception;
    public JobVO getJobListById(Long JobId)throws Exception;
    public JobVO addExpensesById(JobVO jobVO) throws Exception;

}
