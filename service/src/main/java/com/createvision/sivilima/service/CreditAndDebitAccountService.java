package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.CustomerSupplierPaymentVO;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;

import java.util.List;

public interface CreditAndDebitAccountService {

    PaymentDetailVO createNewPayment(PaymentDetailVO paymentDetailVO) throws Exception;

    List<CustomerSupplierPaymentVO> getCustomerPaymentDetailById(Long id) throws Exception;

    List<PaymentDetailVO> getCreditPaymentDetailsById(Long id) throws Exception;

    List<CustomerSupplierPaymentVO> getSupplierPaymentDetailsById(Long id) throws Exception;
}
