package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.dao.InvoiceItemDetailDao;
import com.createvision.sivilima.model.Invoice;
import com.createvision.sivilima.model.InvoiceItemDetail;
import org.springframework.stereotype.Repository;

@Repository("invoiceItemDetailDao")
public class InvoiceItemDetailDaoImpl extends AbstractDaoImpl<InvoiceItemDetail, Long> implements InvoiceItemDetailDao {

}
