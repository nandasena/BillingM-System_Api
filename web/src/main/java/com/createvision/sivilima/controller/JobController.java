package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.JobService;
import com.createvision.sivilima.valuesObject.JobVO;
import com.createvision.sivilima.valuesObject.OtherExpensesVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
