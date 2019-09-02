package com.createvision.sivilima.service;

import com.createvision.sivilima.model.Invoice;
import com.createvision.sivilima.valuesObject.InvoiceVO;
import java.util.List;

public interface InvoiceService {

    List<Invoice> getAllInvoices() throws Exception;

    Invoice getInvoiceById(long id) throws Exception;

    Long saveInvoice(Invoice invoice) throws Exception;

    void updateInvoice(long id, Invoice invoice) throws  Exception;

    void deleteInvoice(long id) throws Exception;

    InvoiceVO createNewInvoice(InvoiceVO invoiceVO)throws Exception;

}
