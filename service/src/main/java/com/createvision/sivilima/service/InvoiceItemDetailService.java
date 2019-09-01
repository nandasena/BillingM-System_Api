package com.createvision.sivilima.service;

import com.createvision.sivilima.model.InvoiceItemDetail;

import java.util.List;

public interface InvoiceItemDetailService {

    List<InvoiceItemDetail> getAllInvoiceItemDetails() throws Exception;

    InvoiceItemDetail getInvoiceById(long id) throws Exception;

    void saveInvoiceItemDetail(InvoiceItemDetail invoiceItemDetail) throws Exception;

    void updateInvoiceItemDetail(long id, InvoiceItemDetail invoiceItemDetail) throws  Exception;

    void deleteInvoiceItemDetail(long id) throws Exception;
}
