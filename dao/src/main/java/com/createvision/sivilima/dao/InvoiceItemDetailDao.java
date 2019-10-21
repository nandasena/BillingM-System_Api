package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.InvoiceItemDetail;

import java.util.List;


public interface InvoiceItemDetailDao extends AbstractDao<InvoiceItemDetail, Long> {

    List<InvoiceItemDetail> gteInvoiceDetailByInvoiceId(Long invoiceId) throws Exception;
}
