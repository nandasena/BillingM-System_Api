package com.createvision.sivilima.dao;
import com.createvision.sivilima.tableModel.Invoice;

import java.util.Date;
import java.util.List;


public interface InvoiceDao extends AbstractDao<Invoice, Long> {

    List<Invoice> getInvoiceByAmount() throws Exception;
    public List<Invoice> sampleJoinQuery () throws Exception;
    public List<Object[]> sampleStoreProcedure()throws Exception;
    public List<Object[]> sampleNativeQuery() throws Exception;
    List<Object[]> getInvoiceByDateRange(Date fromDate, Date toDate) throws Exception;
    List<Object[]> getInvoiceDetailsByDateRange(Date fromDate, Date toDate)throws Exception;
    List<Object[]> getItemWiseDetailsByDateRange(Date fromDate, Date toDate) throws Exception;
}
