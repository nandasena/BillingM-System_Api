package com.createvision.sivilima.controller;


import com.createvision.sivilima.service.SupplierService;
import com.createvision.sivilima.valuesObject.CustomerSupplierVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllSupplier() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CustomerSupplierVO> customerSupplierVOList = supplierService.getAllSupplier();
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(customerSupplierVOList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createSupplier(@RequestBody List<CustomerSupplierVO> customerSupplierVOS) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            CustomerSupplierVO customerSupplierVOList = supplierService.createSupplier(customerSupplierVOS);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(customerSupplierVOList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

}
