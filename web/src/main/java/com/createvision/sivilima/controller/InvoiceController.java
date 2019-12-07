package com.createvision.sivilima.controller;

import com.createvision.sivilima.tableModel.Invoice;
import com.createvision.sivilima.tableModel.User;
import com.createvision.sivilima.service.InvoiceItemDetailService;
import com.createvision.sivilima.service.InvoiceService;
import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/invoice")
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private InvoiceItemDetailService invoiceItemDetailService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllInventories() throws Exception {

        try {
            List<InvoiceVO> invoiceVOS = invoiceService.getAllInvoices();
            ReturnVO returnVO = new ReturnVO();
            returnVO.setResult(invoiceVOS);
            returnVO.setSuccess(true);
            returnVO.setStatusCode(200);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> saveInvoice(@RequestBody InvoiceVO invoiceVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            InvoiceVO insetedInvoice = invoiceService.createNewInvoice(invoiceVO);
            returnVO.setResult(insetedInvoice);
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

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updateInvoice(@RequestBody InvoiceVO invoiceVO) throws Exception {
        try {
            InvoiceVO updateInvoice = invoiceService.updateInvoice(invoiceVO);
            return ResponseEntity.ok(updateInvoice);
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteInvoice(@PathVariable("id") Long id) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            boolean isTrue = invoiceService.deleteInvoice(id);
            returnVO.setSuccess(isTrue);
            returnVO.setStatusCode(200);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(e);
        }

    }

    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getInvoiceDetailsByInvoiceId(@PathVariable("invoiceId") Long invoiceId) throws Exception {

        try {
            List<ItemDetailsVO> invoiceVOS = invoiceService.getInvoiceDetailsByInvoiceId(invoiceId);
            ReturnVO returnVO = new ReturnVO();
            returnVO.setResult(invoiceVOS);
            returnVO.setSuccess(true);
            returnVO.setStatusCode(200);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e);
        }
    }

    @RequestMapping(value = "/fromDate/{fromDate}/toDate/{toDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getInvoiceBydatePeriod(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {

        try {
            List<InvoiceVO> invoiceVOS = invoiceService.getInvoicesByDateRange(fromDate, toDate);
            ReturnVO returnVO = new ReturnVO();
            returnVO.setResult(invoiceVOS);
            returnVO.setSuccess(true);
            returnVO.setStatusCode(200);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e);
        }
    }

    @RequestMapping(value = "/paymentDetail/fromDate/{fromDate}/toDate/{toDate}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getInvoicePaymentDetailByDate(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {

        try {
            List<InvoiceVO> invoiceVOS = invoiceService.getInvoicesByDateRange(fromDate, toDate);
            ReturnVO returnVO = new ReturnVO();
            returnVO.setResult(invoiceVOS);
            returnVO.setSuccess(true);
            returnVO.setStatusCode(200);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e);
        }
    }

    @RequestMapping(value = "paymentDetail/fromDate/{fromDate}/toDate/{toDate}/type/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getInvoicePaymentDetailByDateAndPaymentType(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate, @PathVariable("type") String type) throws Exception {

        try {
            List<PaymentDetailVO> invoiceVOS = invoiceService.getInvoicePaymentDetailByDateAndPaymentType(fromDate, toDate, type);
            ReturnVO returnVO = new ReturnVO();
            returnVO.setResult(invoiceVOS);
            returnVO.setSuccess(true);
            returnVO.setStatusCode(200);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e);
        }
    }

    @RequestMapping(value = "/creditPayment/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createCreditPayment(@RequestBody PaymentDetailVO paymentDetailVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            PaymentDetailVO ReturnPaymentDetailVO = invoiceService.createCreditPayment(paymentDetailVO);
            returnVO.setResult(ReturnPaymentDetailVO);
            if (ReturnPaymentDetailVO != null) {
                returnVO.setSuccess(true);
                returnVO.setStatusCode(200);
            } else {
                returnVO.setSuccess(false);
                returnVO.setStatusCode(500);
            }
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
