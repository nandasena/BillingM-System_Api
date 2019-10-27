package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.service.InvoiceService;
import com.createvision.sivilima.valuesObject.InvoiceVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.UserVO;
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
import java.util.UUID;

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
    PaymentTypeDao paymentTypeDao;

    @Autowired
    ItemCodeDao itemCodeDao;


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
            invoiceVO.setTotalAmount(Double.parseDouble(format.format(invoiceTmp.getTotalAmount())));
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
            ItemCode itemCode = itemCodeDao.getItemCode("INVOICE");
            String code = itemCode.getCode();
            int lastNUmber = itemCode.getLastNumber();
            String lastInvoiceNumber = new Integer(itemCode.getLastNumber()).toString();
            String invoiceNumber = code + "-" + lastInvoiceNumber;
            double totalInvoiceDiscount = 0;
            PaymentType paymentType = paymentTypeDao.get((long) 1);
            saveInvoice.setTotalAmount(invoiceVO.getTotalAmount());
            saveInvoice.setAdvanceAmount(invoiceVO.getAdvanceAmount());
            saveInvoice.setBalanceAmount(invoiceVO.getBalanceAmount());
            saveInvoice.setInvoiceDate(commonFunctions.getDateTimeByDateString(invoiceVO.getInvoiceDate()));
            saveInvoice.setInvoiceNumber(invoiceNumber);
            saveInvoice.setCustomerName(invoiceVO.getCustomerName());
            saveInvoice.setInvoiceDiscount(invoiceVO.getInvoiceDiscount());
            saveInvoice.setPaymentTypeId(paymentType);
            saveInvoice.setCustomerId(invoiceVO.getCustomerId() != null ? invoiceVO.getCustomerId() : null);
            saveInvoice.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            Long id = invoiceDao.save(saveInvoice);
            Invoice insertedInvoice = invoiceDao.get(id);
            itemCode.setLastNumber(++lastNUmber);
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
            insertedInvoice.setTotalDiscount(totalInvoiceDiscount);
            invoiceDao.save(insertedInvoice);

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
            List<Invoice> invoices = invoiceDao.getInvoiceByDateRange(startDate, endDate);

            //LOGGER.info("Invoice count {}", invoices.size());
            for (Invoice invoiceTmp : invoices) {
                User user = invoiceTmp.getUser();
                InvoiceVO invoiceVO = new InvoiceVO();
                invoiceVO.setId(invoiceTmp.getId());
                invoiceVO.setInvoiceNumber(invoiceTmp.getInvoiceNumber());
                invoiceVO.setCustomerName(!invoiceTmp.getCustomerName().isEmpty() ? invoiceTmp.getCustomerName() : "--");
                invoiceVO.setInvoiceDateOfString(dateFormat.format(invoiceTmp.getInvoiceDate()));
                invoiceVO.setTotalAmount(Double.parseDouble(format.format(invoiceTmp.getTotalAmount())));
                invoiceVO.setInvoiceDiscount(invoiceTmp.getTotalDiscount());
                if (user != null) {
                    UserVO userVO = new UserVO();
                    //  BeanUtils.copyProperties(user, userVO);
                    //invoiceVO.setUser(userVO);
                }
                invoiceVOS.add(invoiceVO);
            }
        } catch (Exception e) {
            throw e;
        }

        return invoiceVOS;
    }
}
