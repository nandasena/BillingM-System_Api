package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.QuotationService;
import com.createvision.sivilima.valuesObject.InvoiceVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/quotation")
public class QuotationController {

    @Autowired
    QuotationService quotationService;

    @RequestMapping(value = "/invoice/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> invoice(@RequestBody InvoiceVO invoiceVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            InvoiceVO createQuotationVO = quotationService.createInvoiceQuotation(invoiceVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(invoiceVO);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/invoiceByDateRange/fromDate/{fromDate}/toDate/{toDate}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> invoiceByDateRange(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<InvoiceVO> invoiceVOList = quotationService.invoice(fromDate, toDate);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(invoiceVOList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }


    @RequestMapping(value = "/invoice/{id}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> invoiceDetailsById(@PathVariable("id") Long id) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<ItemDetailsVO> invoiceItemDetailsVO = quotationService.invoiceDetailsById(id);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(invoiceItemDetailsVO);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }

    @RequestMapping(value = "/getInvoiceReprintData/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getInvoiceReprintData(@PathVariable("id") Long id) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            InvoiceVO invoiceVO = quotationService.getInvoiceReprintData(id);

            returnVO.setResult(invoiceVO);
            returnVO.setSuccess(true);
            returnVO.setStatusCode(200);

            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }


}
