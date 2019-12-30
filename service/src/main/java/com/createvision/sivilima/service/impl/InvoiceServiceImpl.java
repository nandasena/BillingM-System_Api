package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.service.InvoiceService;
import com.createvision.sivilima.valuesObject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.lang.Double.parseDouble;

@Service("invoiceService")
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    InvoiceItemDetailDao invoiceItemDetailDao;

    @Autowired
    ItemDetailDao itemDetailDao;

    @Autowired
    CommonFunctionsImpl commonFunctions;

    @Autowired
    ItemCodeDao itemCodeDao;

    @Autowired
    PaymentDetailDao paymentDetailDao;

    @Autowired
    PaymentMethodDao paymentMethodDao;

    @Autowired
    PaymentDetailsOfCreditDao paymentDetailsOfCreditDao;

    @Autowired
    BankDetailDao bankDetailDao;

    @Autowired
    CreditAndDebitAccountDao creditAndDebitAccountDao;

    @Autowired
    CustomerDao customerDao;

    @Override
    public List<InvoiceVO> getAllInvoices() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat format = new DecimalFormat("##.00");
        List<Invoice> invoices = invoiceDao.getAll();
        List<InvoiceVO> invoiceVOS = new ArrayList<>();
        //LOGGER.info("Invoice count {}", invoices.size());
        for (Invoice invoiceTmp : invoices) {
            User user = invoiceTmp.getUser();
            InvoiceVO invoiceVO = new InvoiceVO();
            invoiceVO.setId(invoiceTmp.getId());
            invoiceVO.setInvoiceNumber(invoiceTmp.getInvoiceNumber());
            invoiceVO.setCustomerName(!invoiceTmp.getCustomerName().isEmpty() ? invoiceTmp.getCustomerName() : "--");
            invoiceVO.setInvoiceDateOfString(dateFormat.format(invoiceTmp.getInvoiceDate()));
            invoiceVO.setTotalAmount(parseDouble(format.format(invoiceTmp.getTotalAmount())));
            invoiceVO.setInvoiceDiscount(invoiceTmp.getTotalDiscount());
            if (user != null) {
                UserVO userVO = new UserVO();
                //  BeanUtils.copyProperties(user, userVO);
                //invoiceVO.setUser(userVO);
            }
            invoiceVOS.add(invoiceVO);

        }
        return invoiceVOS;
    }

    @Override
    public Invoice getInvoiceById(long id) throws Exception {
        return invoiceDao.get(id);
    }

    @Override
    public Long saveInvoice(Invoice invoice) throws Exception {
        Long id = invoiceDao.save(invoice);
        return id;
    }

    @Override
    public InvoiceVO updateInvoice(InvoiceVO invoiceVO) throws Exception {
        try {
            Invoice invoiceDB = getInvoiceById(invoiceVO.getId());
            invoiceDB.setTotalAmount(invoiceVO.getTotalAmount());
            invoiceDao.update(invoiceDB);
        } catch (Exception e) {
            throw e;
        }


        return null;
    }

    @Override
    public void deleteInvoice(long id) throws Exception {
        Invoice invoice = getInvoiceById(id);

        invoiceDao.delete(invoice);
    }

    @org.springframework.transaction.annotation.Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
    @Override
    public InvoiceVO createNewInvoice(InvoiceVO invoiceVO) throws Exception {

        //  sampleStoreProcedure();
        InvoiceVO invoiceVO1 = new InvoiceVO();
        Invoice saveInvoice = new Invoice();
        try {
            List<ItemCode> itemCodeList = itemCodeDao.getItemCode("INVOICE");
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
            Long id = invoiceDao.save(saveInvoice);
            Invoice insertedInvoice = invoiceDao.get(id);
            itemCode.setNextNumber(++lastNUmber);
            itemCodeDao.save(itemCode);

            List<ItemVO> itemVOList = new ArrayList<>();
            itemVOList = invoiceVO.getItemList();

            for (ItemVO itemVO : itemVOList) {
                double totalDiscount = itemVO.getSellingQuantity() * itemVO.getPrice() * itemVO.getDiscountPercentage() / 100;
                totalInvoiceDiscount += totalDiscount;
                InvoiceItemDetail invoiceItemDetail = new InvoiceItemDetail();
                Item item = itemDao.get(itemVO.getItemId());
                ItemDetail itemDetail = itemDetailDao.get(itemVO.getItemDetailId());
                double availableQty = itemDetail.getAvailableQuantity();
                availableQty = availableQty - itemVO.getSellingQuantity();
                itemDetail.setAvailableQuantity(availableQty);
                invoiceItemDetail.setItem(item);
                invoiceItemDetail.setItemDetail(itemDetail);
                invoiceItemDetail.setInvoice(insertedInvoice);
                invoiceItemDetail.setSellingQuantity(itemVO.getSellingQuantity());
                invoiceItemDetail.setItemDiscountPercentage(itemVO.getDiscountPercentage());
                invoiceItemDetail.setItemPrice(itemVO.getPrice());
                invoiceItemDetail.setTotalItemDiscount(totalDiscount);
                invoiceItemDetail.setTotalAmount(itemVO.getTotal());

                invoiceItemDetailDao.save(invoiceItemDetail);
                itemDetailDao.save(itemDetail);
            }
            insertedInvoice.setTotalDiscount(commonFunctions.DecimalFormat(totalInvoiceDiscount));
            invoiceDao.save(insertedInvoice);
            invoiceVO.setInvoiceDiscount(commonFunctions.DecimalFormat(totalInvoiceDiscount));
            invoiceVO.setInvoiceNumber(insertedInvoice.getInvoiceNumber());
            invoiceVO.setCustomerName(insertedInvoice.getCustomerName() == null ? "--" : insertedInvoice.getCustomerName());
            List<PaymentDetailVO> paymentDetailVOList = new ArrayList<>();
            paymentDetailVOList = invoiceVO.getPaymentDetailList();

            if (paymentDetailVOList != null) {
                for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
                    PaymentDetails paymentDetails = new PaymentDetails();
                    PaymentMethod  paymentMethod = paymentMethodDao.getPaymentMethodByTypeCode(paymentDetailVO.getTypeCode());
                    paymentDetails.setAmount(paymentDetailVO.getAmount());
                    paymentDetails.setInvoice(insertedInvoice);
                    paymentDetails.setCardNumber(paymentDetailVO.getCardNumber() != null ? paymentDetailVO.getCardNumber() : "-");
                    paymentDetails.setChequeNumber(paymentDetailVO.getChequeNumber() != null ? paymentDetailVO.getChequeNumber() : "-");
                    paymentDetails.setChequeDate(paymentDetailVO.getChequeDate() == null ? null : commonFunctions.getDateTimeByDateString(paymentDetailVO.getChequeDate()));
                    paymentDetails.setChequeDescription(paymentDetailVO.getDescription());
                    if (paymentDetailVO.getBankId() != null) {
                        BankDetail bankDetail = bankDetailDao.get(paymentDetailVO.getBankId());
                        paymentDetails.setBankDetail(bankDetail);
                    } else {
                        paymentDetails.setBankDetail(null);
                    }
                    paymentDetails.setPaymentMethod(paymentMethod);
                    paymentDetailDao.save(paymentDetails);
                }
            }

            Customer customer =new Customer();
            if(invoiceVO.getCustomerId() != null){
                customer = customerDao.get(invoiceVO.getCustomerId());
            }else {
                customer =null;
            }

            CreditAndDebitAccount creditAndDebitAccount = new CreditAndDebitAccount();
            creditAndDebitAccount.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            creditAndDebitAccount.setInvoice(insertedInvoice);
            creditAndDebitAccount.setCustomer(customer);

            if (paymentDetailVOList != null) {
                for (PaymentDetailVO paymentDetailVO : paymentDetailVOList) {
                    PaymentMethod  paymentMethod = paymentMethodDao.getPaymentMethodByTypeCode(paymentDetailVO.getTypeCode());
                    creditAndDebitAccount.setPaymentMethod(paymentMethod);
                    if (paymentMethod.getId() == 4) {
                        creditAndDebitAccount.setCredit(paymentDetailVO.getAmount());
                    } else {
                        creditAndDebitAccount.setDebit(paymentDetailVO.getAmount());
                    }
                    creditAndDebitAccountDao.save(creditAndDebitAccount);
                }

            }



        } catch (Exception e) {
            throw e;
        }

        return invoiceVO;

    }

    public void sampleJoinQuery() throws Exception {
        List<Invoice> test = invoiceDao.sampleJoinQuery();
    }

    public void sampleStoreProcedure() throws Exception {

        List<Object[]> result = invoiceDao.sampleStoreProcedure();
        for (Object[] row : result) {
            System.out.println("sample data ======" + row[0].toString());
            System.out.println("sample data======" + row[6].toString());
            System.out.println("sample data======" + row[7].toString());
        }
    }

    @Override
    public boolean deleteInvoice(Long id) throws Exception {
        boolean isTrue = false;
        try {
            Invoice invoiceDB = getInvoiceById(id);
            if (invoiceDB != null) {
                invoiceDB.setDelete(true);
                Long updatedId = invoiceDao.save(invoiceDB);
                if (updatedId == id) {
                    isTrue = true;
                }
            } else {
                isTrue = false;
            }

        } catch (Exception e) {
            throw e;
        }

        return isTrue;
    }

    @Override
    public List<ItemDetailsVO> getInvoiceDetailsByInvoiceId(Long invoiceId) throws Exception {
        List<ItemDetailsVO> itemDetailsVOList = new ArrayList<>();
        try {
            List<InvoiceItemDetail> invoiceItemDetail = invoiceItemDetailDao.gteInvoiceDetailByInvoiceId(invoiceId);
            if (!invoiceItemDetail.isEmpty()) {
                for (InvoiceItemDetail tem : invoiceItemDetail) {
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
    public List<InvoiceVO> getInvoicesByDateRange(String fromDate, String toDate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat format = new DecimalFormat("##.00");
        List<InvoiceVO> invoiceVOS = new ArrayList<>();
        try {
            Date startDate = commonFunctions.getDateTimeByDateString(fromDate);
            Date endDate = commonFunctions.getDateTimeByDateString(toDate);
            List<Object[]> invoices = invoiceDao.getInvoiceByDateRange(startDate, endDate);

            //LOGGER.info("Invoice count {}", invoices.size());
            for (Object[] invoiceTmp : invoices) {
//                User user = invoiceTmp.getUser();
                InvoiceVO invoiceVO = new InvoiceVO();
                invoiceVO.setId(Long.parseLong(invoiceTmp[0].toString()));
                invoiceVO.setInvoiceNumber(invoiceTmp[1].toString());
                invoiceVO.setCustomerName(!invoiceTmp[2].toString().isEmpty() ? invoiceTmp[2].toString() : "--");
                invoiceVO.setInvoiceDateOfString(dateFormat.format(invoiceTmp[3]));
                invoiceVO.setTotalAmount(parseDouble(format.format(invoiceTmp[4])));
                invoiceVO.setInvoiceDiscount(parseDouble(invoiceTmp[5].toString()));
                invoiceVO.setPaymentType(invoiceTmp[6].toString());
//                if (user != null) {
//                    UserVO userVO = new UserVO();
//                    //  BeanUtils.copyProperties(user, userVO);
//                    //invoiceVO.setUser(userVO);
//                }
                invoiceVOS.add(invoiceVO);
            }
        } catch (Exception e) {
            throw e;
        }

        return invoiceVOS;
    }

    @Override
    public List<PaymentDetailVO> getInvoicePaymentDetailByDateAndPaymentType(String fromDate, String toDate, String type) throws Exception {

        List<PaymentDetailVO> paymentDetailVOList = new ArrayList<>();
        try {
            List<Object[]> paymentDetailList = paymentDetailDao.getPaymentDetailByDateAndType(fromDate, toDate, type);
            for (Object[] tem : paymentDetailList) {
                PaymentDetailVO paymentDetailVO = new PaymentDetailVO();
                paymentDetailVO.setPaidAmount(parseDouble(tem[0].toString()));
                paymentDetailVO.setInvoiceNumber(tem[4].toString());
                paymentDetailVO.setAmount(parseDouble(tem[2].toString()));
                paymentDetailVO.setCustomerName(tem[6] == null ? "--" : tem[6].toString());
                paymentDetailVO.setInvoiceId(Long.parseLong(tem[3].toString()));
                paymentDetailVO.setTypeCode(tem[7].toString());
                paymentDetailVO.setPaymentDetailId(Long.parseLong(tem[8].toString()));
                paymentDetailVOList.add(paymentDetailVO);
            }
        } catch (Exception e) {
            throw e;
        }
        return paymentDetailVOList;
    }

    @Override
    public PaymentDetailVO createCreditPayment(PaymentDetailVO paymentDetailVO) throws Exception {
        PaymentDetailVO insertObject = new PaymentDetailVO();
        try {
            PaymentDetails paymentDetails = paymentDetailDao.get(paymentDetailVO.getPaymentDetailId());
            PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodByTypeCode(paymentDetailVO.getTypeCode());

            PaymentDetailsOfCredit paymentDetailsOfCredit = new PaymentDetailsOfCredit();
            paymentDetailsOfCredit.setAmount(paymentDetailVO.getAmount());
            paymentDetailsOfCredit.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            paymentDetailsOfCredit.setDescription("");
            paymentDetailsOfCredit.setPaymentDetails(paymentDetails);
            paymentDetailsOfCredit.setPaymentMethod(paymentMethod);
            paymentDetailsOfCredit.setCardNumber(paymentDetailVO.getCardNumber() == null ? "--" : paymentDetailVO.getCardNumber());
            if (paymentDetailVO.getBankId() != null) {
                BankDetail bankDetail = bankDetailDao.get(paymentDetailVO.getBankId());
                paymentDetailsOfCredit.setBankDetail(bankDetail);
            } else {
                paymentDetailsOfCredit.setBankDetail(null);
            }
            paymentDetailsOfCredit.setChequeDescription(paymentDetailVO.getDescription());
            paymentDetailsOfCredit.setChequeDate(paymentDetailVO.getChequeDate() == null ? null : commonFunctions.getDateTimeByDateString(paymentDetailVO.getChequeDate()));
            paymentDetailsOfCredit.setChequeNumber(paymentDetailVO.getChequeNumber());

            Long insertId = paymentDetailsOfCreditDao.save(paymentDetailsOfCredit);
            if (insertId != null) {
                insertObject = paymentDetailVO;
            } else {
                insertObject = null;
            }


        } catch (Exception e) {
            throw e;
        }
        return insertObject;
    }

    @Override
    public InvoiceVO getInvoiceReprintData(Long id) throws Exception {
        InvoiceVO invoiceVO = new InvoiceVO();
        try {
            Invoice invoice = invoiceDao.get(id);
            invoiceVO.setInvoiceNumber(invoice.getInvoiceNumber());
            invoiceVO.setInvoiceDate(commonFunctions.convertDateToString(invoice.getInvoiceDate()));
            invoiceVO.setTotalAmount(invoice.getTotalAmount());
            invoiceVO.setCustomerName(invoice.getCustomerName() == null ? "--" : invoice.getCustomerName());
            invoiceVO.setInvoiceDiscount(invoice.getTotalDiscount());
            Set<InvoiceItemDetail> invoiceItemDetails = invoice.getInvoiceItemDetails();
            List<ItemVO> itemVOList = new ArrayList<>();
            for (InvoiceItemDetail temInvoiceItemDetail : invoiceItemDetails) {
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
