package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.AbstractDao;
import com.createvision.sivilima.dao.PaymentDetailDao;
import com.createvision.sivilima.tableModel.PaymentDetails;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentDetailDao")
public class PaymentDetailDaoImpl extends AbstractDaoImpl<PaymentDetails,Long> implements PaymentDetailDao {

    @Override
    public List<Object[]> getPaymentDetailByDateAndType(String fromDate, String toDate, String type) throws Exception {
        Query query = getSession().createSQLQuery("CALL getInvoiceDetailsByTypeCodeAndDate(?,?,?)");
        query.setParameter(0,type);
        query.setParameter(1,fromDate);
        query.setParameter(2,toDate);
        List<Object[]> result = query.list();
        return result;
    }
}
