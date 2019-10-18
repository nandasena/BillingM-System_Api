package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PaymentTypeDao;
import com.createvision.sivilima.tableModel.PaymentType;
import org.springframework.stereotype.Repository;

@Repository("paymentTypeDao")
public class PaymentTypeImpl extends AbstractDaoImpl<PaymentType,Long> implements PaymentTypeDao {
}
