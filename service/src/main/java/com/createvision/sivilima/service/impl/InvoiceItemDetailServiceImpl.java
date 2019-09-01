package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.InvoiceItemDetailDao;
import com.createvision.sivilima.model.InvoiceItemDetail;
import com.createvision.sivilima.service.InvoiceItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("invoiceItemDetailService")
@Transactional
public class InvoiceItemDetailServiceImpl implements InvoiceItemDetailService {

    @Autowired
    private InvoiceItemDetailDao invoiceItemDetailDao;

    @Override
    public List<InvoiceItemDetail> getAllInvoiceItemDetails() throws Exception {
        return invoiceItemDetailDao.getAll();
    }

    @Override
    public InvoiceItemDetail getInvoiceById(long id) throws Exception {
        return invoiceItemDetailDao.get(id);
    }

    @Override
    public void saveInvoiceItemDetail(InvoiceItemDetail invoiceItemDetail) throws Exception {
        invoiceItemDetailDao.save(invoiceItemDetail);
    }

    @Override
    public void updateInvoiceItemDetail(long id, InvoiceItemDetail invoiceItemDetail) throws Exception {
        InvoiceItemDetail invoiceItemDetailDB = getInvoiceById(id);
        //set new invoice item detail

        invoiceItemDetailDao.update(invoiceItemDetail);
    }

    @Override
    public void deleteInvoiceItemDetail(long id) throws Exception {
        InvoiceItemDetail invoiceItemDetail = getInvoiceById(id);
        invoiceItemDetailDao.delete(invoiceItemDetail);
    }
}
