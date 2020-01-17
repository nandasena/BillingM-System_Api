package com.createvision.sivilima.controller;


import com.createvision.sivilima.service.SupplierService;
import com.createvision.sivilima.valuesObject.CustomerVO;
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
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllSupplier() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CustomerVO> customerVOList = supplierService.getAllSupplier();
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(customerVOList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

}
