package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.InvoiceItemReturnDao;
import com.createvision.sivilima.tableModel.InvoiceItemReturn;
import org.springframework.stereotype.Repository;

@Repository("invoiceItemReturnDao")
public class InvoiceItemReturnDaoImpl extends AbstractDaoImpl<InvoiceItemReturn, Long> implements InvoiceItemReturnDao {
}
