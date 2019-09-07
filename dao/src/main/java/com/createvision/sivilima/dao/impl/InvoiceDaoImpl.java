package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.model.Invoice;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends AbstractDaoImpl<Invoice, Long> implements InvoiceDao {

    @Override
    public List<Invoice> getInvoiceByAmount() throws Exception {

        Criteria c= getSession().createCriteria(Invoice.class)
        .add(Restrictions.eq("advanceAmount",2000.00));

        return c.list();
    }

    @Override
    public List<Object> testJoin () throws Exception {

        Criteria IinCri= getSession().createCriteria(Invoice.class).add(Restrictions.eq("advanceAmount",2000.00));
        Criteria It =IinCri.createCriteria("invoiceItemDetails");
        Criteria ITD =It.createCriteria("item").add(Restrictions.eq("name","pasan"));
        List result =IinCri.list();

        return result;
    }
}
