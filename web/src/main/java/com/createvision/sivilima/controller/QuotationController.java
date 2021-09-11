package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.QuotationService;
import com.createvision.sivilima.valuesObject.InvoiceVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
