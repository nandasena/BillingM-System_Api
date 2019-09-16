package com.createvision.sivilima.controller;

import com.createvision.sivilima.model.Invoice;
import com.createvision.sivilima.model.InvoiceItemDetail;
import com.createvision.sivilima.model.Item;
import com.createvision.sivilima.model.User;
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
@RequestMapping("/invoices")
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

        LOGGER.info("Invoice testing");

        try {
//            Invoice invoice = new Invoice();
//            invoice.setTotalAmount(1000.00);
//            invoice.setAdvanceAmount(2000.00);
//            invoice.setBalanceAmount(500.00);
//            invoice.setElivaryDate(new Date());
//            invoice.setInvoiceDate(new Date());
//            invoice.setInvoiceNumber(UUID.randomUUID().toString());
//            invoiceService.saveInvoice(invoice);
//
//            Item item = new Item();
//            item.setCreateDate(new Date());
//            item.setDescription("Testing");
//            item.setLastUpdateDate(new Date());
//            item.setName("Item 1");
//            itemService.saveItem(item);
//
//            InvoiceItemDetail invoiceItemDetail = new InvoiceItemDetail();
//            invoiceItemDetail.setInvoice(invoice);
//            invoiceItemDetail.setItem(item);
//            invoiceItemDetailService.saveInvoiceItemDetail(invoiceItemDetail);
//
//            InvoiceItemDetail invoiceItemDetail2 = new InvoiceItemDetail();
//            invoiceItemDetail2.setInvoice(invoice);
//            invoiceItemDetail2.setItem(item);
//            invoiceItemDetailService.saveInvoiceItemDetail(invoiceItemDetail2);
//
//            Set<InvoiceItemDetail> invoiceItemDetails = new HashSet<>();
//            invoiceItemDetails.add(invoiceItemDetail);
//            invoiceItemDetails.add(invoiceItemDetail2);
//            invoice.setInvoiceItemDetails(invoiceItemDetails);

            List<Invoice> invoices = invoiceService.getAllInvoices();
            List<InvoiceVO> invoiceVOS = new ArrayList<>();
            ReturnVO returnVO =new ReturnVO();
            LOGGER.info("Invoice count {}", invoices.size());
            for (Invoice invoiceTmp : invoices) {
                User user = invoiceTmp.getUser();
                InvoiceVO invoiceVO = new InvoiceVO();
                invoiceVO.setInvoiceNumber(invoiceTmp.getInvoiceNumber());
                if(user !=null){
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
             //   invoiceVO.setUser(userVO);

                }
                invoiceVOS.add(invoiceVO);
                returnVO.setResult(invoiceVOS);
                returnVO.setSuccess(true);
                returnVO.setStatusCode(200);

            }

            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> saveInvoice (@RequestBody InvoiceVO invoiceVO) throws Exception {
        ReturnVO returnVO =new ReturnVO();
        try {
            InvoiceVO insetedInvoice = invoiceService.createNewInvoice(invoiceVO);
            returnVO.setResult(insetedInvoice);
            returnVO.setSuccess(true);
            returnVO.setStatusCode(200);
            return ResponseEntity.ok(returnVO);
        }catch (Exception e){
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
            InvoiceVO updateInvoice =invoiceService.updateInvoice(invoiceVO);
            return ResponseEntity.ok(updateInvoice);
        }catch (Exception e){
            return ResponseEntity.ok(e);
        }

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteInvoice(@PathVariable("id") Long id) throws Exception {
        try {
            InvoiceVO saveInvoice =new InvoiceVO();
            return ResponseEntity.ok(saveInvoice);
        }catch (Exception e){
            return ResponseEntity.ok(e);
        }

    }
}
