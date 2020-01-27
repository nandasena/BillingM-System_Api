package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.CustomerService;
import com.createvision.sivilima.valuesObject.CustomerSupplierVO;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllCustomer() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CustomerSupplierVO> customerSupplierVOList = customerService.getAllCustomer();
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
