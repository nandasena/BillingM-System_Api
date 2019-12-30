package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.PaymentDetailVO;

public interface CreditAndDebitAccountService {

    PaymentDetailVO createNewPayment(PaymentDetailVO paymentDetailVO) throws Exception;
}
