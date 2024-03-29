package com.createvision.sivilima.service;

import com.createvision.sivilima.tableModel.Invoice;
import com.createvision.sivilima.valuesObject.InvoiceVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;

import java.util.List;

public interface InvoiceService {

    List<InvoiceVO> getAllInvoices() throws Exception;

    Invoice getInvoiceById(long id) throws Exception;

    Long saveInvoice(Invoice invoice) throws Exception;

    InvoiceVO updateInvoice(InvoiceVO invoice) throws Exception;

    void deleteInvoice(long id) throws Exception;

    InvoiceVO createNewInvoice(InvoiceVO invoiceVO) throws Exception;

    boolean deleteInvoice(Long id) throws Exception;

    List<ItemDetailsVO> getInvoiceDetailsByInvoiceId(Long invoiceId) throws Exception;

    List<InvoiceVO> getInvoicesByDateRange(String fromDate, String toDate) throws Exception;

    List<PaymentDetailVO> getInvoicePaymentDetailByDateAndPaymentType(String fromDate, String toDate, String type) throws Exception;

    PaymentDetailVO createCreditPayment(PaymentDetailVO paymentDetailVO) throws Exception;

    InvoiceVO getInvoiceReprintData(Long id) throws Exception;

    List<InvoiceVO> addReturnInvoiceItem(InvoiceVO invoiceVO) throws Exception;



}
