package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.AbstractDao;
import com.createvision.sivilima.dao.PaymentDetailDao;
import com.createvision.sivilima.tableModel.PaymentDetails;
import org.springframework.stereotype.Repository;

@Repository("paymentDetailDao")
public class PaymentDetailDaoImpl extends AbstractDaoImpl<PaymentDetails,Long> implements PaymentDetailDao {
}
