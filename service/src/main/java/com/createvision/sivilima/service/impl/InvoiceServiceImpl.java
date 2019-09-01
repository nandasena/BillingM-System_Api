package com.createvision.sivilima.service.impl;
import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.model.Invoice;
import com.createvision.sivilima.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("invoiceService")
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;


    @Override
    public List<Invoice> getAllInvoices() throws Exception {
        return invoiceDao.getAll();
    }

    @Override
    public Invoice getInvoiceById(long id) throws Exception {
        return invoiceDao.get(id);
    }

    @Override
    public void saveInvoice(Invoice invoice) throws Exception {
        invoiceDao.save(invoice);
    }

    @Override
    public void updateInvoice(long id, Invoice invoice) throws Exception {
        Invoice invoiceDB = getInvoiceById(id);
        invoiceDB.setTotalAmount(invoice.getTotalAmount());

        invoiceDao.update(invoiceDB);
    }

    @Override
    public void deleteInvoice(long id) throws Exception{
        Invoice invoice = getInvoiceById(id);

        invoiceDao.delete(invoice);
    }
}
