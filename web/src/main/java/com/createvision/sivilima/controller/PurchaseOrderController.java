package com.createvision.sivilima.controller;


import com.createvision.sivilima.service.IPurchaseOrderService;
import com.createvision.sivilima.valuesObject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/purchaseOrder")

public class PurchaseOrderController {

    @Autowired
    IPurchaseOrderService purchaseOrderService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createPurchaseOrder(@RequestBody PurchaseOrderVO purchaseOrderVO) {
        ReturnVO returnVO = new ReturnVO();

        try {
            PurchaseOrderVO returnPurchaseOrderVO = purchaseOrderService.createPurchaseOrder(purchaseOrderVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(returnPurchaseOrderVO);
            return ResponseEntity.ok(returnVO);

        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }

    @RequestMapping(value = "/fromDate/{fromDate}/toDate/{toDate}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllPurchaseOrder(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
        ReturnVO returnVO = new ReturnVO();

        try {
            List<PurchaseOrderVO> returnPurchaseOrderVO = purchaseOrderService.getAllPurchaseOrder(fromDate, toDate);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(returnPurchaseOrderVO);
            return ResponseEntity.ok(returnVO);

        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }

    @RequestMapping(value = "/getPurchaseOrderDetailById/{id}/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getPurchaseOrderDetailById(@PathVariable("id") Long id) {
        ReturnVO returnVO = new ReturnVO();

        try {
            List<ItemDetailsVO> purchaseOrderDetailList = purchaseOrderService.getPurchaseOrderDetailById(id);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(purchaseOrderDetailList);
            return ResponseEntity.ok(returnVO);

        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }

    }

    @RequestMapping(value = "/getBranch/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getBranch() {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<BranchVO>branchVOList =purchaseOrderService.getBranch();
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(branchVOList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }
}
