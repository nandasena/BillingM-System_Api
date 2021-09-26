package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.QuotationService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.lang.Double.parseDouble;


@Service("quotationService")
@Transactional
public class QuotationServiceImpl implements QuotationService {

    @Autowired
    ItemCodeDao itemCodeDao;

    @Autowired
    CommonFunctionsImpl commonFunctions;

    @Autowired
    QuotationDao quotationDao;

    @Autowired
    QuotationDetailsDao quotationDetailsDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    ItemDetailDao itemDetailDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    TempCustomerDao tempCustomerDao;

    @Override
    public InvoiceVO createInvoiceQuotation(InvoiceVO invoiceVO) throws Exception {

        InvoiceVO invoiceVO1 = new InvoiceVO();
        Quotation saveQuotation = new Quotation();
        try {
            List<ItemCode> itemCodeList = itemCodeDao.getItemCode("QUOTATION");
            TempCustomerVO tempCustomerVO = invoiceVO.getTempCustomerVO();
            ItemCode itemCode = itemCodeList.get(itemCodeList.size() - 1);
            String code = itemCode.getCode();
            int lastNUmber = itemCode.getNextNumber();
            String lastInvoiceNumber = new Integer(itemCode.getNextNumber()).toString();
            String invoiceNumber = code + "-" + lastInvoiceNumber;
            double totalInvoiceDiscount = 0;
            saveQuotation.setTotalAmount(invoiceVO.getTotalAmount());
            saveQuotation.setAdvanceAmount(invoiceVO.getAdvanceAmount());
            saveQuotation.setBalanceAmount(invoiceVO.getBalanceAmount());
            saveQuotation.setInvoiceDate(commonFunctions.getDateTimeByDateString(invoiceVO.getInvoiceDate()));
            saveQuotation.setInvoiceNumber(invoiceNumber);
            saveQuotation.setCustomerName(invoiceVO.getCustomerName());
            saveQuotation.setCustomerId(invoiceVO.getCustomerId() != null ? invoiceVO.getCustomerId() : null);
            saveQuotation.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            saveQuotation.setCustomerName(tempCustomerVO.getFirstName());
            Long id = quotationDao.save(saveQuotation);
            Quotation insertedQuotation = quotationDao.get(id);
            itemCode.setNextNumber(++lastNUmber);
            itemCodeDao.save(itemCode);

            List<ItemVO> itemVOList = new ArrayList<>();
            itemVOList = invoiceVO.getItemList();

            for (ItemVO itemVO : itemVOList) {
                QuotationDetails quotationDetails = new QuotationDetails();
                double totalDiscount = 0.00;
                double cashDiscount = 0.00;

                if (itemVO.getTypeOfDiscount() == 1) {
                    quotationDetails.setDiscount_type(DiscountType.CASH_DISCOUNT);
                    totalDiscount = itemVO.getPriceDiscount() * itemVO.getSellingQuantity();
                } else {
                    quotationDetails.setDiscount_type(DiscountType.PERCENTAGE_DISCOUNT);
                    totalDiscount = itemVO.getSellingQuantity() * itemVO.getPrice() * itemVO.getDiscountPercentage() / 100;
                }
                totalInvoiceDiscount += totalDiscount;

                Item item = itemDao.get(itemVO.getItemId());
                ItemDetail itemDetail = itemDetailDao.get(itemVO.getItemDetailId());
                quotationDetails.setItem(item);
                quotationDetails.setItemDetail(itemDetail);
                quotationDetails.setQuotation(insertedQuotation);
                quotationDetails.setSellingQuantity(itemVO.getSellingQuantity());
                quotationDetails.setItemDiscountPercentage(itemVO.getDiscountPercentage());
                quotationDetails.setItemPrice(itemVO.getPrice());
                quotationDetails.setTotalItemDiscount(totalDiscount);
                quotationDetails.setTotalAmount(itemVO.getTotal());
                quotationDetails.setItemCost(itemDetail.getCostPrice());
                quotationDetails.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));

                if (itemVO.getTypeOfPrice() == 1) {
                    quotationDetails.setPrice_type(PriceType.MRP_PRICE);
                } else if (itemVO.getTypeOfPrice() == 2) {
                    quotationDetails.setPrice_type(PriceType.FABRICATOR_PRICE);
                } else {
                    quotationDetails.setPrice_type(PriceType.SHOWROOM_PRICE);
                }
                quotationDetailsDao.save(quotationDetails);
//                itemDetailDao.save(itemDetail);
            }

            insertedQuotation.setTotalDiscount(commonFunctions.DecimalFormat(totalInvoiceDiscount));
            quotationDao.save(insertedQuotation);
            invoiceVO.setInvoiceDiscount(commonFunctions.DecimalFormat(totalInvoiceDiscount));
            invoiceVO.setInvoiceNumber(insertedQuotation.getInvoiceNumber());
            invoiceVO.setCustomerName(insertedQuotation.getCustomerName() == null ? "--" : insertedQuotation.getCustomerName());
            List<PaymentDetailVO> paymentDetailVOList = new ArrayList<>();
            paymentDetailVOList = invoiceVO.getPaymentDetailList();


            Customer customer = new Customer();
            if (invoiceVO.getCustomerId() != null) {
                customer = customerDao.get(invoiceVO.getCustomerId());
            } else {
                customer = null;
            }
            TempCustomer tempCustomer = new TempCustomer();
            tempCustomer.setFirstName(tempCustomerVO.getFirstName());
            tempCustomer.setAddress1(tempCustomerVO.getAddress1());
            tempCustomer.setTelephoneNo(tempCustomerVO.getContactNumber());
            tempCustomer.setQuotation(insertedQuotation);
            Long insertedTempCustomerId = tempCustomerDao.save(tempCustomer);
            if (insertedTempCustomerId != null) {
                invoiceVO.setTempCustomerVO(tempCustomerVO);
            }

        } catch (Exception e) {
            throw e;
        }

        return invoiceVO;
    }


    @Override
    public List<InvoiceVO> invoice(String fromDate, String toDate) throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat format = new DecimalFormat("##.00");
        List<InvoiceVO> invoiceVOS = new ArrayList<>();
        //LOGGER.info("Invoice count {}", invoices.size());
        Date startDate = commonFunctions.getDateTimeByDateString(fromDate);
        Date endDate = commonFunctions.getDateTimeByDateString(toDate);
        List<Object[]> QuotationInvoicesList = quotationDao.getInvoiceByDateRange(startDate, endDate);
        for (Object[] invoiceTmp : QuotationInvoicesList) {
            InvoiceVO invoiceVO = new InvoiceVO();
            invoiceVO.setId(Long.parseLong(invoiceTmp[0].toString()));
            invoiceVO.setInvoiceNumber(invoiceTmp[1].toString());
            invoiceVO.setCustomerName(!invoiceTmp[2].toString().isEmpty() ? invoiceTmp[2].toString() : "--");
            invoiceVO.setInvoiceDateOfString(dateFormat.format(invoiceTmp[3]));
            invoiceVO.setTotalAmount(parseDouble(format.format(invoiceTmp[4])));
            invoiceVO.setInvoiceDiscount(parseDouble(invoiceTmp[5].toString()));
//            invoiceVO.setPaymentType(invoiceTmp[6].toString());
            invoiceVOS.add(invoiceVO);

        }
        return invoiceVOS;

    }

    @Override
    public List<ItemDetailsVO> invoiceDetailsById(Long id) throws Exception {
        List<ItemDetailsVO> itemDetailsVOList = new ArrayList<>();
        try {
            List<QuotationDetails> invoiceItemDetail = quotationDetailsDao.gteInvoiceDetailByInvoiceId(id);
            if (!invoiceItemDetail.isEmpty()) {
                for (QuotationDetails tem : invoiceItemDetail) {
                    ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                    itemDetailsVO.setMrpPrice(tem.getItemPrice());
                    itemDetailsVO.setItemName(tem.getItem().getName());
                    itemDetailsVO.setQuantity(tem.getSellingQuantity());
                    itemDetailsVO.setTotalItemAmount(tem.getTotalAmount());
                    itemDetailsVO.setTotalItemDiscount(tem.getTotalItemDiscount());
                    itemDetailsVO.setItemId(tem.getItem().getId());
                    itemDetailsVOList.add(itemDetailsVO);

                }
            }

        } catch (Exception e) {
            throw e;
        }
        return itemDetailsVOList;
    }

    @Override
    public InvoiceVO getInvoiceReprintData(Long id) throws Exception {
        InvoiceVO invoiceVO = new InvoiceVO();
        try {
            Quotation invoice = quotationDao.get(id);
         //   TempCustomer tempCustomer = tempCustomerDao.getCustomerDetailsById(id);

            invoiceVO.setInvoiceNumber(invoice.getInvoiceNumber());
            invoiceVO.setInvoiceDate(commonFunctions.convertDateToString(invoice.getInvoiceDate()));
            invoiceVO.setTotalAmount(invoice.getTotalAmount());
            invoiceVO.setCustomerName(invoice.getCustomerName() == null ? "--" : invoice.getCustomerName());
            invoiceVO.setInvoiceDiscount(invoice.getTotalDiscount());
            invoiceVO.setAdvanceAmount(invoice.getAdvanceAmount());

//            if(tempCustomer !=null){
//                TempCustomerVO tempCustomerVO =new TempCustomerVO();
//                tempCustomerVO.setFirstName(tempCustomer.getFirstName());
//                tempCustomerVO.setAddress1(tempCustomer.getAddress1());
//                tempCustomerVO.setContactNumber(tempCustomer.getTelephoneNo());
//                invoiceVO.setTempCustomerVO(tempCustomerVO);
//            }else{
                TempCustomerVO tempCustomerVO =new TempCustomerVO();
                tempCustomerVO.setFirstName("");
                tempCustomerVO.setAddress1("");
                tempCustomerVO.setContactNumber("");
                invoiceVO.setTempCustomerVO(tempCustomerVO);
//            }


            Set<QuotationDetails> invoiceItemDetails = invoice.getInvoiceItemDetails();
            List<ItemVO> itemVOList = new ArrayList<>();
            for (QuotationDetails temInvoiceItemDetail : invoiceItemDetails) {
                ItemVO itemVO = new ItemVO();
                itemVO.setItemName(temInvoiceItemDetail.getItem().getName());
                itemVO.setItemDiscount(temInvoiceItemDetail.getTotalItemDiscount());
                itemVO.setTotal(temInvoiceItemDetail.getTotalAmount());
                itemVO.setPrice(temInvoiceItemDetail.getItemPrice());
                itemVO.setSellingQuantity(temInvoiceItemDetail.getSellingQuantity());
                itemVOList.add(itemVO);
            }
            invoiceVO.setItemList(itemVOList);

        } catch (Exception e) {

        }
        return invoiceVO;
    }
}
