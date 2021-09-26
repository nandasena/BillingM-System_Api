package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.Quotation;

import java.util.Date;
import java.util.List;

public interface QuotationDao extends AbstractDao<Quotation, Long> {
    List<Object[]> getInvoiceByDateRange(Date fromDate, Date toDate) throws Exception;
}
