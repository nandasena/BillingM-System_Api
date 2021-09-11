package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.QuotationService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.InvoiceVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;
import com.createvision.sivilima.valuesObject.TempCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service("quotationService")
@Transactional
public class QuotationServiceImpl implements QuotationService {

    @Autowired
    private ItemCodeDao itemCodeDao;

    @Autowired
    CommonFunctionsImpl commonFunctions;

    @Autowired
    private QuotationDao quotationDao;

    @Autowired
    private QuotationDetailsDao quotationDetailsDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemDetailDao itemDetailDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    TempCustomerDao tempCustomerDao;

    @Override
    public InvoiceVO createInvoiceQuotation(InvoiceVO invoiceVO) throws Exception {

        InvoiceVO invoiceVO1 = new InvoiceVO();
        Quotation saveInvoice = new Quotation();
        try {
            List<ItemCode> itemCodeList = itemCodeDao.getItemCode("QUOTATION");
            TempCustomerVO tempCustomerVO = invoiceVO.getTempCustomerVO();
            ItemCode itemCode = itemCodeList.get(itemCodeList.size() - 1);
            String code = itemCode.getCode();
            int lastNUmber = itemCode.getNextNumber();
            String lastInvoiceNumber = new Integer(itemCode.getNextNumber()).toString();
            String invoiceNumber = code + "-" + lastInvoiceNumber;
            double totalInvoiceDiscount = 0;
            saveInvoice.setTotalAmount(invoiceVO.getTotalAmount());
            saveInvoice.setAdvanceAmount(invoiceVO.getAdvanceAmount());
            saveInvoice.setBalanceAmount(invoiceVO.getBalanceAmount());
            saveInvoice.setInvoiceDate(commonFunctions.getDateTimeByDateString(invoiceVO.getInvoiceDate()));
            saveInvoice.setInvoiceNumber(invoiceNumber);
            saveInvoice.setCustomerName(invoiceVO.getCustomerName());
            saveInvoice.setCustomerId(invoiceVO.getCustomerId() != null ? invoiceVO.getCustomerId() : null);
            saveInvoice.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            saveInvoice.setCustomerName(tempCustomerVO.getFirstName());
            Long id = quotationDao.save(saveInvoice);
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
}
