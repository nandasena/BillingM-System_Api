package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.CustomerPaymentVO;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;

import java.util.List;

public interface CreditAndDebitAccountService {

     PaymentDetailVO createNewPayment(PaymentDetailVO paymentDetailVO) throws Exception;
    List<CustomerPaymentVO> getCustomerPaymentDetailById(Long id) throws Exception;
}
