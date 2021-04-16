package com.createvision.sivilima.controller;


import com.createvision.sivilima.service.IReportService;
import com.createvision.sivilima.valuesObject.InvoiceDetailsReportVO;
import com.createvision.sivilima.valuesObject.PurchaseOrderVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/report")
public class ReportController {


    @Autowired
    IReportService reportService;

    @RequestMapping(value = "/invoiceByDateRange/fromDate/{fromDate}/toDate/{toDate}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getInvoiceDetailsByDateRange(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
        ReturnVO returnVO = new ReturnVO();

        try {
           List<InvoiceDetailsReportVO> returnInvoiceDetailsVOList = reportService.getInvoiceDetailsByDateRange(fromDate, toDate);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(returnInvoiceDetailsVOList);
            return ResponseEntity.ok(returnVO);

        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }

    @RequestMapping(value = "/itemDetailsByDateRange/fromDate/{fromDate}/toDate/{toDate}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getSellingItemDetailsByDate(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
        ReturnVO returnVO = new ReturnVO();

        try {
            List<InvoiceDetailsReportVO> returnItemDetailsVOList = reportService.getSellingItemDetailsByDate(fromDate, toDate);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(returnItemDetailsVOList);
            return ResponseEntity.ok(returnVO);

        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }
}
