package com.createvision.sivilima.service.impl;


import com.createvision.sivilima.service.IReportService;
import com.createvision.sivilima.valuesObject.InvoiceDetailsReportVO;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service("reportService")
@Transactional
public class ReportServiceImpl implements IReportService {
    @Override
    public List<InvoiceDetailsReportVO> getInvoiceDetailsByDateRange(String fromDate, String toDate) throws Exception {
        return null;
    }
}
