package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.PaymentDetails;

import java.util.List;

public interface PaymentDetailDao extends AbstractDao<PaymentDetails, Long>  {
    List<Object[]> getPaymentDetailByDateAndType(String fromDate,String toDate,String type) throws Exception;
}
