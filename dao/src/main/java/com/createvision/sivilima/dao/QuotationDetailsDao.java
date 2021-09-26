package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.QuotationDetails;

import java.util.List;

public interface QuotationDetailsDao extends AbstractDao<QuotationDetails, Long> {
    List<QuotationDetails> gteInvoiceDetailByInvoiceId(Long invoiceId) throws Exception;
}
