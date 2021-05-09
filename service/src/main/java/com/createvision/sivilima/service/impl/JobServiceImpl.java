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
                jobDetails.setTotalItemDiscount(totalDiscount);
                jobDetails.setExpenses(Math.round((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) * 100.0) / 100.0);
                jobDetails.setNetExpenses(Math.round(((itemVO.getSellingQuantity() * itemDetail.getCostPrice()) - totalDiscount) * 100.0) / 100.0);
                jobDetails.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                jobDetails.setItemQuantity(itemVO.getSellingQuantity());
                jobDetails.setItemCost(itemDetail.getCostPrice());
                jobDetails.setPrice_type(PriceType.Cost);
                jobDetails.setJob(jobDao.get(jobId));
                jobDetails.setExpensesType(ExpensesType.Item);
                jobDetailsDao.save(jobDetails);
                itemDetailDao.save(itemDetail);

            }
            Job insertJob = jobDao.get(jobId);
            insertJob.setDiscount(totalJobDiscount);
            insertJob.setCost(totalItemCost);
            jobDao.save(insertJob);
            //

        } catch (Exception e) {
            throw e;
        }

        return null;
    }
}
