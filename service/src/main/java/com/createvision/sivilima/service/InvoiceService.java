package com.createvision.sivilima.service;

import com.createvision.sivilima.model.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getAllInvoices() throws Exception;

    Invoice getInvoiceById(long id) throws Exception;

    void saveInvoice(Invoice invoice) throws Exception;

    void updateInvoice(long id, Invoice invoice) throws  Exception;

    void deleteInvoice(long id) throws Exception;
}
