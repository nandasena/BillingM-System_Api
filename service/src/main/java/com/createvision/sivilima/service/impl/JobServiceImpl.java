package com.createvision.sivilima.service.impl;


import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.JobService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.JobVO;
import com.createvision.sivilima.valuesObject.OtherExpensesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("jobService")
@Transactional
public class JobServiceImpl implements JobService {
    @Autowired
    OtherExpensesDao otherExpensesDao;

    @Autowired
    JobDao jobDao;

    @Autowired
    JobDetailsDao jobDetailsDao;

    @Autowired
    CommonFunctionsImpl commonFunctions;

    @Autowired
    ItemCodeDao itemCodeDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    ItemDetailDao itemDetailDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    ReceivedItemDao receivedItemDao;

    @Override
    public List<OtherExpensesVO> getAllOtherExpenses() throws Exception {
        List<OtherExpensesVO> otherExpensesVOList = new ArrayList<>();

        try {
            List<OtherExpenses> otherExpensesList = otherExpensesDao.getAll();

            for (OtherExpenses expenses : otherExpensesList) {
                OtherExpensesVO otherExpensesVO = new OtherExpensesVO();

                otherExpensesVO.setId(expenses.getId());
                otherExpensesVO.setName(expenses.getName());
                otherExpensesVO.setDescription(expenses.getDescription());

                otherExpensesVOList.add(otherExpensesVO);

            }

        } catch (Exception e) {
            throw e;
        }
        return otherExpensesVOList;
    }

    @Override
    public JobVO createJob(JobVO jobVO) throws Exception {

        try {

            // set job Number.....
            List<ItemCode> itemCodeList = itemCodeDao.getItemCode("JOB");
            ItemCode itemCode = itemCodeList.get(itemCodeList.size() - 1);
            String code = itemCode.getCode();
            int lastNUmber = itemCode.getNextNumber();
            String lastJobNumber = new Integer(itemCode.getNextNumber()).toString();
            String jobNumber = code + "-" + lastJobNumber;
            itemCode.setNextNumber(++lastNUmber);
            itemCodeDao.save(itemCode);
            jobVO.setJobNumber(jobNumber);
            //

            // job
            double amount = jobVO.getRatePerSquareFeet() * jobVO.getSquareFeet();
            amount = Math.round(amount * 100.0) / 100.0;
            Job job = new Job();
            job.setJobName(jobVO.getName());
            job.setDescription(jobVO.getDescription());
            job.setJobNumber(jobNumber);
            job.setAmount(amount);
            job.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            job.setCustomerId(customerDao.get(jobVO.getCustomerId()));
            job.setStartDate(commonFunctions.getDateTimeByDateString(jobVO.getStartDate()));
            job.setEndDate(commonFunctions.getDateTimeByDateString(jobVO.getEndDate()));
            job.setTotalSquareFeet(jobVO.getSquareFeet());
            job.setRatePerSqareFeet(jobVO.getRatePerSquareFeet());
            job.setJobStatus(JobStatus.valueOf("CREATE"));

            Long jobId = jobDao.save(job);
            //

            // job Details
            double totalJobDiscount = 0;
            double totalItemDiscount = 0;
            double totalItemCost = 0;
            List<ItemVO> itemVOList = new ArrayList<>();
            itemVOList = jobVO.getItemVOList();

            for (ItemVO itemVO : itemVOList) {
                JobDetails jobDetails = new JobDetails();
                double totalDiscount = 0.00;
                double cashDiscount = 0.00;
                jobDetails.setDiscount_type(DiscountType.NONE);
                totalJobDiscount += totalDiscount;
                Item item = itemDao.get(itemVO.getItemId());
                ItemDetail itemDetail = itemDetailDao.get(itemVO.getItemDetailId());
                double availableQty = itemDetail.getAvailableQuantity();
                availableQty = availableQty - itemVO.getSellingQuantity();
                itemDetail.setAvailableQuantity(availableQty);
                totalItemCost += Math.round(((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) - totalDiscount) * 100.0) / 100.0;
                jobDetails.setItem(item);
                jobDetails.setItemDetail(itemDetail);
                jobDetails.setTotalItemDiscount(totalDiscount);
                jobDetails.setExpenses(Math.round((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) * 100.0) / 100.0);
                jobDetails.setNetExpenses(Math.round(((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) - totalDiscount) * 100.0) / 100.0);
                jobDetails.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                jobDetails.setItemQuantity(itemVO.getSellingQuantity());
                jobDetails.setItemCost(itemDetail.getCostPrice());
                jobDetails.setPrice_type(PriceType.Cost);
                jobDetails.setJob(jobDao.get(jobId));
                jobDetails.setDescription("");
                jobDetails.setExpensesType(ExpensesType.Item);
                jobDetailsDao.save(jobDetails);
                itemDetailDao.save(itemDetail);

            }
            Job insertJob = jobDao.get(jobId);
            insertJob.setDiscount(totalJobDiscount);
            insertJob.setCost(Math.round(totalItemCost * 100.0) / 100.0);
            jobDao.save(insertJob);
            //

        } catch (Exception e) {
            throw e;
        }

        return jobVO;
    }

    @Override
    public List<JobVO> getJobList() throws Exception {
        List<JobVO> jobVOList = new ArrayList<>();
        try {
            List<Job> jobList = jobDao.getJobList();

            for (Job j : jobList) {
                JobVO jobVO = new JobVO();
                jobVO.setJobId(j.getId());
                jobVO.setName(j.getJobName());
                jobVO.setJobNumber(j.getJobNumber());
                jobVOList.add(jobVO);
            }

        } catch (Exception e) {
            throw e;
        }

        return jobVOList;
    }

    @Override
    public JobVO getJobListById(Long JobId) throws Exception {
        JobVO jobVO = new JobVO();
        List<ItemVO> itemVOList = new ArrayList<>();
        try {
            Job job = jobDao.get(JobId);
            if (job != null) {
                Set<JobDetails> jobDetailsList = job.getJobDetails();
                jobVO.setJobId(job.getId());
                jobVO.setJobNumber(job.getJobNumber());
                jobVO.setName(job.getJobName());
                JobStatus jobStatus = job.getJobStatus();
                jobVO.setStatus(jobStatus.name());
                jobVO.setStartDate(commonFunctions.convertDateToString(job.getStartDate()));
                jobVO.setEndDate(commonFunctions.convertDateToString(job.getEndDate()));
                jobVO.setSquareFeet(job.getTotalSquareFeet());
                jobVO.setRatePerSquareFeet(job.getRatePerSqareFeet());

                for (JobDetails jd : jobDetailsList) {
                    ItemVO itemVO = new ItemVO();
                    itemVO.setItemId(jd.getItem() != null ? jd.getItem().getId() : null);
                    itemVO.setItemDetailId(jd.getItemDetail() != null ? jd.getItemDetail().getId() : null);
                    itemVO.setItemName(jd.getItem() != null ? jd.getItem().getName() : "");
                    itemVO.setSellingQuantity(jd.getItemQuantity());
                    itemVO.setPrice(jd.getItemCost());
                    itemVO.setTotal(jd.getExpenses());
                    itemVOList.add(itemVO);

                }
                jobVO.setItemVOList(itemVOList);
            }


        } catch (Exception e) {
            throw e;
        }
        return jobVO;
    }

    @Override
    public JobVO addExpensesById(JobVO jobVO) throws Exception {

        try {
            Job job = jobDao.get(jobVO.getJobId());
            double totalExpenses = 0.00;
            for (OtherExpensesVO e : jobVO.getOtherExpensesVOList()) {
                JobDetails jobDetails = new JobDetails();
                totalExpenses += e.getAmount();
                jobDetails.setExpenses(e.getAmount());
                jobDetails.setDiscount_type(DiscountType.NONE);
                jobDetails.setPrice_type(PriceType.Cost);
                jobDetails.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));

                jobDetails.setJob(job);
                jobDetails.setDescription(e.getDescription());
                if (e.getId() == 1) {
                    jobDetails.setExpensesType(ExpensesType.TRANSPORT);
                } else if (e.getId() == 2) {
                    jobDetails.setExpensesType(ExpensesType.WAGES);
                } else if (e.getId() == 3) {
                    jobDetails.setExpensesType(ExpensesType.OTHER);
                }
                jobDetailsDao.save(jobDetails);
            }
            job.setCost(Math.round((job.getCost() + totalExpenses) * 100.0) / 100.0);
            jobDao.save(job);


        } catch (Exception e) {
            throw e;
        }
        return jobVO;
    }

    @Override
    public JobVO addItmById(JobVO jobVO) throws Exception {
        try {
            double amount = jobVO.getRatePerSquareFeet() * jobVO.getSquareFeet();
            amount = Math.round(amount * 100.0) / 100.0;
            Job selectedJob = jobDao.get(jobVO.getJobId());
            selectedJob.setStartDate(commonFunctions.getDateTimeByDateString(jobVO.getStartDate()));
            selectedJob.setEndDate(commonFunctions.getDateTimeByDateString(jobVO.getEndDate()));
            selectedJob.setTotalSquareFeet(jobVO.getSquareFeet());
            selectedJob.setRatePerSqareFeet(jobVO.getRatePerSquareFeet());
            selectedJob.setAmount(amount);
            double totalItemCost = 0;
            double totalJobDiscount = 0;
            for (ItemVO itemVO : jobVO.getItemVOList()) {
                JobDetails jobDetails = new JobDetails();
                double totalDiscount = 0.00;
                double cashDiscount = 0.00;
                jobDetails.setDiscount_type(DiscountType.NONE);
                totalJobDiscount += totalDiscount;
                Item item = itemDao.get(itemVO.getItemId());
                ItemDetail itemDetail = itemDetailDao.get(itemVO.getItemDetailId());
                double availableQty = itemDetail.getAvailableQuantity();
                availableQty = availableQty - itemVO.getSellingQuantity();
                itemDetail.setAvailableQuantity(availableQty);
                totalItemCost += Math.round(((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) - totalDiscount) * 100.0) / 100.0;
                jobDetails.setItem(item);
                jobDetails.setTotalItemDiscount(totalDiscount);
                jobDetails.setExpenses(Math.round((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) * 100.0) / 100.0);
                jobDetails.setNetExpenses(Math.round(((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) - totalDiscount) * 100.0) / 100.0);
                jobDetails.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                jobDetails.setItemQuantity(itemVO.getSellingQuantity());
                jobDetails.setItemCost(itemDetail.getCostPrice());
                jobDetails.setPrice_type(PriceType.Cost);
                jobDetails.setJob(jobDao.get(selectedJob.getId()));
                jobDetails.setDescription("");
                jobDetails.setExpensesType(ExpensesType.Item);
                jobDetailsDao.save(jobDetails);
                itemDetailDao.save(itemDetail);
            }
            totalItemCost += selectedJob.getCost();
            selectedJob.setCost(Math.round(totalItemCost * 100.0) / 100.0);
            jobDao.save(selectedJob);

            return jobVO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public JobVO removeReceivedItemsById(JobVO jobVO) throws Exception {

        try {
            List<ItemVO> itemVOList = jobVO.getItemVOList();
            Job job = jobDao.get(jobVO.getJobId());
            for (ItemVO i : itemVOList) {
                double avalableQuantityl =0;
                ItemReceived itemReceived =new ItemReceived();
                ItemDetail itemDetail = itemDetailDao.get(i.getItemDetailId());
                Item item = itemDao.get(i.getItemId());
                itemReceived.setReceivedQuantity(i.getReceivedQuantity());
                itemReceived.setItemId(item);
                itemReceived.setItemDetail(itemDetail);
                itemReceived.setJobId(job);
                itemReceived.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                receivedItemDao.save(itemReceived);

                avalableQuantityl = itemDetail.getAvailableQuantity();
                avalableQuantityl =avalableQuantityl+i.getReceivedQuantity();
                itemDetail.setAvailableQuantity(avalableQuantityl);
                itemDetailDao.save(itemDetail);
            }


        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}
