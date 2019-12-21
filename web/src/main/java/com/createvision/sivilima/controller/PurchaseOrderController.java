package com.createvision.sivilima.controller;


import com.createvision.sivilima.service.IPurchaseOrderService;
import com.createvision.sivilima.valuesObject.PurchaseOrderVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/purchaseOrder")

public class PurchaseOrderController {

    @Autowired
    IPurchaseOrderService purchaseOrderService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createPurchaseOrder(PurchaseOrderVO purchaseOrderVO) {
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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllPurchaseOrder(PurchaseOrderVO purchaseOrderVO) {
        ReturnVO returnVO = new ReturnVO();

        try {
            List<PurchaseOrderVO> returnPurchaseOrderVO = purchaseOrderService.getAllPurchaseOrder();
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
}
