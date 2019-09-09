package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.model.Invoice;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
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
    public List<Invoice> testJoin () throws Exception {

//        Criteria IinCri= getSession().createCriteria(Invoice.class,"in");
//        Criteria It =IinCri.createCriteria("invoiceItemDetails","itd");
//        Criteria ITD =It.createCriteria("item","it");
//        IinCri.add(Restrictions.eq("advanceAmount",2000.00));
//        ITD.add(Restrictions.eq("name","pasan"));
//        List<Invoice> result =IinCri.list();

        Criteria IinCri= getSession().createCriteria(Invoice.class,"invoice");
                 IinCri.createAlias("invoice.invoiceItemDetails","invoiceItemDetails");
                 IinCri.createAlias("invoiceItemDetails.item","invoiceItem");
                 IinCri.add(Restrictions.eq("invoiceItem.name","pasan"));
                 IinCri.add(Restrictions.eq("invoice.advanceAmount",2000.00));
                 List<Invoice> result =IinCri.list();

        return result;
    }
}
