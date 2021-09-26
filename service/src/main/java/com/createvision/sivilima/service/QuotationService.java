package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.InvoiceVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;

import java.util.List;

public interface QuotationService {

    InvoiceVO createInvoiceQuotation(InvoiceVO invoiceVO) throws Exception;
    List<ItemDetailsVO> invoiceDetailsById(Long id) throws Exception;
    List<InvoiceVO> invoice(String fromDate, String toDate)throws Exception;
    InvoiceVO getInvoiceReprintData(Long id) throws Exception;
}
