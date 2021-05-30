package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.JobService;
import com.createvision.sivilima.valuesObject.JobVO;
import com.createvision.sivilima.valuesObject.OtherExpensesVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @RequestMapping(value = "/createJob/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createJob(@RequestBody JobVO jobVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            JobVO addedJob = jobService.createJob(jobVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(addedJob);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/otherExpenses/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllOtherExpenses() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<OtherExpensesVO> otherExpensesList = jobService.getAllOtherExpenses();
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(otherExpensesList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/getJobList/",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object>getJobList()throws Exception{
        ReturnVO returnVO = new ReturnVO();
        try {
            List<JobVO> jobListList = jobService.getJobList();
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(jobListList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/getJobListById/{JobId}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object>getJobListById(@PathVariable("JobId") Long JobId)throws Exception{
        ReturnVO returnVO = new ReturnVO();
        try {
            JobVO job = jobService.getJobListById(JobId);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(job);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }
    @RequestMapping(value = "/addExpensesById/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addExpensesById(@RequestBody JobVO jobVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            JobVO addedExpenses = jobService.addExpensesById(jobVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(addedExpenses);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }
    @RequestMapping(value = "/addItemsById/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addItemsById(@RequestBody JobVO jobVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            JobVO addedExpenses = jobService.addItmById(jobVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(addedExpenses);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }

    @RequestMapping(value = "/removeReceivedItemsById/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> removeReceivedItemsById(@RequestBody JobVO jobVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            JobVO addedExpenses = jobService.removeReceivedItemsById(jobVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(addedExpenses);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }




}
