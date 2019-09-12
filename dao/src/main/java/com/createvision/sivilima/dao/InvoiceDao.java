package com.createvision.sivilima.dao;
import com.createvision.sivilima.model.Invoice;
import java.util.List;


public interface InvoiceDao extends AbstractDao<Invoice, Long> {

    List<Invoice> getInvoiceByAmount() throws Exception;
    public List<Invoice> sampleJoinQuery () throws Exception;
    public List<Object[]> sampleStoreProcedure()throws Exception;
    public List<Object[]> sampleNativeQuery() throws Exception;
}
