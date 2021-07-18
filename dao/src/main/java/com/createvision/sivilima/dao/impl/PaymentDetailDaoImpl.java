package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.PaymentDetailDao;
import com.createvision.sivilima.tableModel.IncomeOrExpenses;
import com.createvision.sivilima.tableModel.PaymentDetails;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public List<PaymentDetails> getPaymentDetailsByJobId(Long jobId) throws Exception {
        Criteria IinCri = getSession().createCriteria(PaymentDetails.class, "paymentDetails");
        IinCri.createAlias("paymentDetails.job", "job");
        IinCri.add(Restrictions.eq("job.id", jobId));
        IinCri.add(Restrictions.eq("paymentDetails.incomeOrExpenses", IncomeOrExpenses.INCOME));
        List<PaymentDetails> result = IinCri.list();
        return result;

    }
}
