package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.CreditAndDebitAccountService;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/CreditAndDebitAccountPayment")
public class CreditAndDebitAccountController {

    @Autowired
    CreditAndDebitAccountService creditAndDebitAccountService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createNewPayment(@RequestBody PaymentDetailVO paymentDetailVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            PaymentDetailVO paymentDetai = creditAndDebitAccountService.createNewPayment(paymentDetailVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(paymentDetai);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }
}