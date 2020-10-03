package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.PaymentDetailsOfCredit;

import java.util.List;

public interface PaymentDetailsOfCreditDao extends AbstractDao<PaymentDetailsOfCredit, Long>  {
    List getPaymentDetailsById(Long id) throws Exception;
}
