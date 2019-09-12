package com.createvision.sivilima.dao;

import com.createvision.sivilima.model.Invoice;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface InvoiceDao extends AbstractDao<Invoice, Long> {

    List<Invoice> getInvoiceByAmount() throws Exception;
    public List<Object[]> testJoin () throws Exception;
}
