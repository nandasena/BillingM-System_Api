package com.createvision.sivilima.dao.impl;


import com.createvision.sivilima.dao.QuotationDao;
import com.createvision.sivilima.tableModel.Quotation;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("QuotationDao")
public class QuotationDaoImpl extends AbstractDaoImpl<Quotation,Long> implements QuotationDao {
    @Override
    public List<Object[]> getInvoiceByDateRange(Date fromDate, Date toDate) throws Exception {
        Query query = getSession().createSQLQuery("CALL getQuotationInvoiceByDateRange(?,?)");
        query.setParameter(0,fromDate);
        query.setParameter(1,toDate);
        List<Object[]> result = query.list();
        return result;
    }
}
