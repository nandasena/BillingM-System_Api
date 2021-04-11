package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.InvoiceDetailsReportVO;

import java.util.List;

public interface IReportService {
    public List<InvoiceDetailsReportVO> getInvoiceDetailsByDateRange(String fromDate,String toDate) throws Exception;
}
