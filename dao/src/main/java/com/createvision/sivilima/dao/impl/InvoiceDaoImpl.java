package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.tableModel.Invoice;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends AbstractDaoImpl<Invoice, Long> implements InvoiceDao {

    @Override
    public List<Invoice> getInvoiceByAmount() throws Exception {

        Criteria c = getSession().createCriteria(Invoice.class)
                .add(Restrictions.eq("advanceAmount", 2000.00));

        return c.list();
    }

    @Override
    public List<Invoice> sampleJoinQuery() throws Exception {

        Criteria IinCri = getSession().createCriteria(Invoice.class, "invoice");
        IinCri.createAlias("invoice.invoiceItemDetails", "invoiceItemDetails");
        IinCri.createAlias("invoiceItemDetails.item", "invoiceItem");
        IinCri.add(Restrictions.eq("invoiceItem.name", "pasan"));
        IinCri.add(Restrictions.eq("invoice.advanceAmount", 2000.00));
        List<Invoice> result = IinCri.list();
        return result;
    }

    @Override
    public List<Object[]> sampleStoreProcedure() throws Exception {
        Query query = getSession().createSQLQuery("CALL getAllInvoie(2000.00)");
        List<Object[]> result = query.list();
        return result;
    }

    @Override
    public List<Object[]> sampleNativeQuery() throws Exception {
        Query query = getSession().createSQLQuery("select iid.*,items.name from invoices ins " +
                "left JOIN invoice_item_Details iid " +
                "on ins.id = iid.invoice_id " +
                "left JOIN items on iid.item_id = items.id " +
                "where items.name =\"pasan\" and ins.advance_amount =2000;");
        List<Object[]> result = query.list();
        return result;
    }

    @Override
    public List<Object[]> getInvoiceByDateRange(Date fromDate, Date toDate) throws Exception {
        Query query = getSession().createSQLQuery("CALL getInvoiceByDateRange(?,?)");
        query.setParameter(0,fromDate);
        query.setParameter(1,toDate);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getInvoiceDetailsByDateRange(Date fromDate, Date toDate) throws Exception {
        Query query = getSession().createSQLQuery("CALL getInvoiceDetailsByDateRange(?,?)");
        query.setParameter(0,fromDate);
        query.setParameter(1,toDate);
        List<Object[]> result = query.list();
        return result;
    }

    @Override
    public List<Object[]> getItemWiseDetailsByDateRange(Date fromDate, Date toDate) throws Exception {
        Query query = getSession().createSQLQuery("CALL getItemWiseDetailsByDateRange(?,?)");
        query.setParameter(0,fromDate);
        query.setParameter(1,toDate);
        List<Object[]> result = query.list();
        return result;
    }
}
