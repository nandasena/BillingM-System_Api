package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.model.Invoice;
import org.springframework.stereotype.Repository;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends AbstractDaoImpl<Invoice, Long> implements InvoiceDao {

}
