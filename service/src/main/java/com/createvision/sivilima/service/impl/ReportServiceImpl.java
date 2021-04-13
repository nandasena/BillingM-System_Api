package com.createvision.sivilima.service.impl;


import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.service.IReportService;
import com.createvision.sivilima.valuesObject.InvoiceDetailsReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("reportService")
@Transactional
public class ReportServiceImpl implements IReportService {
    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    CommonFunctionsImpl commonFunctions;

    @Override
    public List<InvoiceDetailsReportVO> getInvoiceDetailsByDateRange(String fromDate, String toDate) throws Exception {
        List<InvoiceDetailsReportVO> invoiceDetailsReportVOList = new ArrayList<>();
        try {
            Date FromDate = commonFunctions.getDateTimeByDateString(fromDate);
            Date ToDate = commonFunctions.getDateTimeByDateString(toDate);

            List<Object[]> invoiceDetailsListObject =invoiceDao.getInvoiceDetailsByDateRange(FromDate,ToDate);

            for (Object[] tem : invoiceDetailsListObject ) {
                InvoiceDetailsReportVO invoiceDetailsReportVO =new InvoiceDetailsReportVO();
                invoiceDetailsReportVO.setInvoiceId(Long.parseLong(tem[0].toString()));
                invoiceDetailsReportVO.setInvoiceNumber(tem[1].toString());
                invoiceDetailsReportVO.setTotalAmount(Double.parseDouble(tem[2].toString()));
                invoiceDetailsReportVO.setInvoiceDiscount(Double.parseDouble(tem[3].toString()));
                invoiceDetailsReportVO.setTotalDiscount(Double.parseDouble(tem[4].toString()));
                invoiceDetailsReportVO.setInvoiceDate(tem[5].toString());
                invoiceDetailsReportVO.setItemPrice(Double.parseDouble(tem[6].toString()));
                invoiceDetailsReportVO.setSellingQuantity(Double.parseDouble(tem[7].toString()));
                invoiceDetailsReportVO.setTotalItemDiscount(Double.parseDouble(tem[8].toString()));
                invoiceDetailsReportVO.setItemCost(Double.parseDouble(tem[9].toString()));
                invoiceDetailsReportVO.setDiscountType(tem[11].toString());
                invoiceDetailsReportVO.setTotalCost(Double.parseDouble(tem[12].toString()));
                invoiceDetailsReportVO.setItemName(tem[13].toString());
                invoiceDetailsReportVO.setProfitOrLost(Double.parseDouble(tem[2].toString())-Double.parseDouble(tem[12].toString()));
                invoiceDetailsReportVOList.add(invoiceDetailsReportVO);
            }

            return invoiceDetailsReportVOList;
        } catch (Exception e) {
            throw e;
        }

    }
}
