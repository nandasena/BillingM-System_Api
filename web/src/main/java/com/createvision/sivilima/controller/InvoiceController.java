package com.createvision.sivilima.controller;

import com.createvision.sivilima.tableModel.Invoice;
import com.createvision.sivilima.tableModel.User;
import com.createvision.sivilima.service.InvoiceItemDetailService;
import com.createvision.sivilima.service.InvoiceService;
import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.InvoiceVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import com.createvision.sivilima.valuesObject.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
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
}
