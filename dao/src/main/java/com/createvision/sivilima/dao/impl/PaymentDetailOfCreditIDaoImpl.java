package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PaymentDetailsOfCreditDao;
import com.createvision.sivilima.tableModel.PaymentDetailsOfCredit;
import org.springframework.stereotype.Repository;

@Repository("paymentDetailsOfCreditDao")
public class PaymentDetailOfCreditIDaoImpl extends AbstractDaoImpl<PaymentDetailsOfCredit,Long> implements PaymentDetailsOfCreditDao {
}
