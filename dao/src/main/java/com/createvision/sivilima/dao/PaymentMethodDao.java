package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.PaymentMethod;

public interface PaymentMethodDao extends AbstractDao<PaymentMethod, Long>   {
    PaymentMethod getPaymentMethodByTypeCode(String code)throws Exception;
}
