package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.IPurchaseOrderService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.longBitsToDouble;
import static java.lang.Double.parseDouble;

@Service("purchaseOrderService")
@Transactional
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);
    @Autowired
    PurchaseOrderDao purchaseOrderDao;

    @Autowired
    CommonFunctionsImpl commonFunction;

    @Autowired
    SupplierDao supplierDao;

    @Autowired
    IUserDao userDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    PurchaseOrderDetailDao purchaseOrderDetailDao;

    @Autowired
    ItemCodeDao itemCodeDao;

    @Autowired
    BranchDao branchDao;

    @Autowired
    GoodReceivedDao goodReceivedDao;

    @Autowired
    GoodReceivedDetailDao goodReceivedDetailDao;

    @Autowired
    ItemDetailDao itemDetailDao;

    @Autowired
    PaymentDetailDao paymentDetailDao;

    @Autowired
    BankDetailDao bankDetailDao;

    @Autowired
    ChequePaymentDetailDao chequePaymentDetailDao;

    @Autowired
    CreditAndDebitAccountDao creditAndDebitAccountDao;

    @Autowired
    PaymentMethodDao paymentMethodDao;

    @Autowired
    CreditorDao creditorDao;


    @Override
    public PurchaseOrderVO createPurchaseOrder(PurchaseOrderVO purchaseOrderVO) throws Exception {
        PurchaseOrderVO insertedOrder = new PurchaseOrderVO();
        try {
            List<ItemCode> itemCodeList = itemCodeDao.getItemCode("PURCHASE");
            ItemCode itemCode = itemCodeList.get(itemCodeList.size() - 1);
            String code = itemCode.getCode();
            int lastNUmber = itemCode.getNextNumber();
            String lastPurchaseCode = new Integer(itemCode.getNextNumber()).toString();
            String purchaseCode = code + "-" + lastPurchaseCode;
            itemCode.setNextNumber(++lastNUmber);

            itemCodeDao.save(itemCode);

            Supplier supplier = supplierDao.get(purchaseOrderVO.getSupplierId());
            User user = userDao.get(purchaseOrderVO.getUserId());
            Branch branch = branchDao.get(purchaseOrderVO.getBranchId());

            PurchaseOrder purchaseOrder = new PurchaseOrder();

            purchaseOrder.setDescription(purchaseOrderVO.getDescription());
            purchaseOrder.setCreatedAt(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
            purchaseOrder.setEstimateReceiveDate(commonFunction.getDateTimeByDateString(purchaseOrderVO.getEstimateReceiveDate()));
            purchaseOrder.setSupplier(supplier);
            purchaseOrder.setUser(user);
            purchaseOrder.setPurchaseCode(purchaseCode);
            purchaseOrder.setBranch(branch);

            Long id = purchaseOrderDao.save(purchaseOrder);
            PurchaseOrder insertedPurchaseOrder = purchaseOrderDao.get(id);

            double totalAmount = 0;
            double totalDiscount = 0;
            List<ItemVO> itemVOList = new ArrayList<>();
            itemVOList = purchaseOrderVO.getItemVOList();
            if (!itemVOList.isEmpty()) {
                for (ItemVO itemVO : itemVOList) {
                    PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
                    Item item = itemDao.get(itemVO.getItemId());
                    ItemDetail itemDetail = itemDetailDao.get(itemVO.getItemDetailId());
                    double itemTotal = itemVO.getPrice() * itemVO.getSellingQuantity();
                    double discountTotal = (itemVO.getDiscountPercentage() * itemVO.getSellingQuantity() * itemVO.getPrice() / 100);
                    purchaseOrderDetail.setTotal(itemTotal);
                    purchaseOrderDetail.setPrice(itemVO.getPrice());
                    purchaseOrderDetail.setQty(itemVO.getSellingQuantity());
                    purchaseOrderDetail.setItem(item);
                    purchaseOrderDetail.setPurchaseOrder(insertedPurchaseOrder);
                    purchaseOrderDetail.setTotalDiscount(discountTotal);
                    purchaseOrderDetail.setDiscountPercentage(itemVO.getDiscountPercentage());
                    purchaseOrderDetail.setItemDetail(itemDetail);
                    purchaseOrderDetailDao.save(purchaseOrderDetail);
                    totalAmount += itemTotal;
                    totalDiscount += discountTotal;
                }
            }

            insertedPurchaseOrder.setTotalAmount(totalAmount);
            insertedPurchaseOrder.setTotalDiscount(totalDiscount);
            purchaseOrderDao.save(insertedPurchaseOrder);

            insertedOrder.setAddress1(supplier.getAddress1());
            insertedOrder.setAddress2(supplier.getAddress2());
            insertedOrder.setSupplierName(supplier.getFirstName());
            insertedOrder.setPurchaseCode(purchaseCode);
            insertedOrder.setTotalDiscount(totalDiscount);
            insertedOrder.setTotalAmount(totalAmount);
            insertedOrder.setPurchaseOrderDate(commonFunction.convertDateToString(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo")));

        } catch (Exception e) {
            throw e;
        }
        return insertedOrder;
    }

    @Override
    public List<PurchaseOrderVO> getAllPurchaseOrder(String fromDate, String toDate) throws Exception {
        List<PurchaseOrderVO> purchaseOrderVOList = new ArrayList<>();
        try {
            List<Object[]> purchaseOrderList = purchaseOrderDao.getAllPurchaseOrder(commonFunction.getDateTimeByDateString(fromDate), commonFunction.getDateTimeByDateString(toDate));

            for (Object[] purchaseOrder : purchaseOrderList) {
                PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();

                purchaseOrderVO.setPurchaseOrderId(Long.parseLong(purchaseOrder[0].toString()));
                purchaseOrderVO.setPurchaseCode(purchaseOrder[1].toString());
                purchaseOrderVO.setSupplierName(purchaseOrder[2].toString());
                purchaseOrderVO.setAddress1(purchaseOrder[3].toString());
                purchaseOrderVO.setAddress2(purchaseOrder[4].toString());
                purchaseOrderVO.setUserName(purchaseOrder[5].toString());
                purchaseOrderVO.setEstimateReceiveDate(purchaseOrder[6].toString());
                purchaseOrderVO.setTotalAmount(parseDouble(purchaseOrder[7].toString()));
                purchaseOrderVO.setTotalDiscount(parseDouble(purchaseOrder[8].toString()));
                purchaseOrderVO.setUserId(Long.parseLong(purchaseOrder[9].toString()));
                purchaseOrderVO.setSupplierCode(purchaseOrder[10].toString());

                purchaseOrderVOList.add(purchaseOrderVO);

            }
        } catch (Exception e) {
            throw e;
        }
        return purchaseOrderVOList;
    }

    @Override
    public List<ItemDetailsVO> getPurchaseOrderDetailById(Long id) throws Exception {
        List<ItemDetailsVO> itemDetailsVOList = new ArrayList<>();
        try {
            PurchaseOrder purchaseOrderList = purchaseOrderDao.get(id);
            List<PurchaseOrderDetail> purchaseOrderDetailList = purchaseOrderList.getPurchaseOrderDetails();

            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetailList) {
                ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                itemDetailsVO.setCostPrice(purchaseOrderDetail.getPrice());
                itemDetailsVO.setQuantity(purchaseOrderDetail.getQty());
                itemDetailsVO.setTotalItemAmount(purchaseOrderDetail.getTotal());
                itemDetailsVO.setTotalItemDiscount(purchaseOrderDetail.getTotalDiscount());
                itemDetailsVO.setReceivedQuantity(purchaseOrderDetail.getReceivedQTY());
                itemDetailsVO.setItemName(purchaseOrderDetail.getItem().getName());
                itemDetailsVO.setItemId(purchaseOrderDetail.getItem().getId());
                itemDetailsVO.setItemDetailId(purchaseOrderDetail.getItemDetail().getId());

                itemDetailsVOList.add(itemDetailsVO);
            }

        } catch (Exception e) {
            throw e;
        }
        return itemDetailsVOList;
    }

    @Override
    public List<BranchVO> getBranch() throws Exception {
        try {
            List<BranchVO> branchVOList = new ArrayList<>();
            List<Branch> branchList = branchDao.getAll();
            if (!branchList.isEmpty()) {
                for (Branch b : branchList) {

                    BranchVO branchVO = new BranchVO();
                    branchVO.setId(b.getId());
                    branchVO.setName(b.getName());

                    branchVOList.add(branchVO);
                }
            }
            return branchVOList;
        } catch (Exception e) {
            throw e;

        }

    }

    @Override
    public List<PurchaseOrderVO> getPurchaseOrderIds() throws Exception {
        try {
            List<PurchaseOrderVO> purchaseOrderVOList = new ArrayList();
            List<PurchaseOrder> purchaseOrderList = purchaseOrderDao.getAll();
            for (PurchaseOrder purchaseOrder : purchaseOrderList) {
                PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
                List<PurchaseOrderDetail> purchaseOrderDetailList = purchaseOrder.getPurchaseOrderDetails();

                for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetailList) {
                    if (purchaseOrderDetail.getQty() != purchaseOrderDetail.getReceivedQTY()) {
                        purchaseOrderVO.setPurchaseOrderId(purchaseOrder.getId());
                        purchaseOrderVO.setPurchaseCode(purchaseOrder.getPurchaseCode());
                        purchaseOrderVO.setSupplierId(purchaseOrder.getSupplier().getId());
                        purchaseOrderVOList.add(purchaseOrderVO);
                        break;
                    }
                }

            }
            return purchaseOrderVOList;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public GoodReceivedVO saveGoodReceived(GoodReceivedVO goodReceivedVO) throws Exception {

        try {
            PurchaseOrder purchaseOrder = purchaseOrderDao.get(goodReceivedVO.getPurchaseOrderId());
            User user = userDao.get((long) 1);
            GoodReceived goodReceived = new GoodReceived();

            goodReceived.setPurchaseOrder(purchaseOrder);
            goodReceived.setUser(user);
            goodReceived.setReceivedDate(commonFunction.getDateTimeByDateString(goodReceivedVO.getReceivedDate()));
            Long saveId = goodReceivedDao.save(goodReceived);
            GoodReceived insertedGoodReceivedObject = goodReceivedDao.get(saveId);
            List<ItemDetailsVO> itemDetailsVOList = goodReceivedVO.getItemDetailsVOList();

            for (ItemDetailsVO itemDetail : itemDetailsVOList) {

                GoodReceiveDetail goodReceiveDetail = new GoodReceiveDetail();
                Item item = itemDao.get(itemDetail.getItemId());
                ItemDetail itemDetailInfor = itemDetailDao.get(itemDetail.getItemDetailId());
                double availableQTY = itemDetailInfor.getAvailableQuantity();
                double NowAVGCost = itemDetailInfor.getCostPrice();
                double NewAVGCost = commonFunction.DecimalFormat((availableQTY * NowAVGCost + itemDetail.getReceiveQuantity() * itemDetail.getCostPrice()) / (availableQTY + itemDetail.getReceiveQuantity()));
                availableQTY = availableQTY + itemDetail.getReceiveQuantity();

                itemDetailInfor.setAvailableQuantity(availableQTY);
                itemDetailInfor.setCostPrice(NewAVGCost);

                goodReceiveDetail.setItem(item);
                goodReceiveDetail.setGoodReceived(insertedGoodReceivedObject);
                goodReceiveDetail.setReceivedQTY(itemDetail.getReceiveQuantity());
                goodReceiveDetail.setGoodReceivedDate(commonFunction.getDateTimeByDateString(goodReceivedVO.getReceivedDate()));

                goodReceivedDetailDao.save(goodReceiveDetail);
                itemDetailDao.save(itemDetailInfor);

                List<PurchaseOrderDetail> purchaseOrderDetailList = purchaseOrderDetailDao.getPurchaseOrderByIdAndItemId(itemDetail.getItemId(), goodReceivedVO.getPurchaseOrderId());
                for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetailList) {
                    double receivedQTY = purchaseOrderDetail.getReceivedQTY();
                    double orderQuantity = purchaseOrderDetail.getQty();
                    receivedQTY = receivedQTY + itemDetail.getReceiveQuantity();
                    if (orderQuantity >= receivedQTY) {
                        purchaseOrderDetail.setReceivedQTY(receivedQTY);
                        purchaseOrderDetailDao.save(purchaseOrderDetail);

                    } else {

                    }
                }
            }

            List<PaymentDetailVO> paymentDetailsList = goodReceivedVO.getPaymentDetailsList();

            if (paymentDetailsList != null) {
                for (PaymentDetailVO paymentDetailVO : paymentDetailsList) {
                    PaymentDetails paymentDetails = new PaymentDetails();
                    PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodByTypeCode(paymentDetailVO.getTypeCode());
                    paymentDetails.setAmount(paymentDetailVO.getAmount());
                    paymentDetails.setIncomeOrExpenses(IncomeOrExpenses.EXPENSES);
                    paymentDetails.setGoodReceived(insertedGoodReceivedObject);
                    paymentDetails.setCardNumber(paymentDetailVO.getCardNumber() != null ? paymentDetailVO.getCardNumber() : "-");
                    paymentDetails.setChequeNumber(paymentDetailVO.getChequeNumber() != null ? paymentDetailVO.getChequeNumber() : "-");
                    paymentDetails.setChequeDate(paymentDetailVO.getChequeDate() == null ? null : commonFunction.getDateTimeByDateString(paymentDetailVO.getChequeDate()));
                    paymentDetails.setChequeDescription(paymentDetailVO.getDescription());


                    if (paymentDetailVO.getBankId() != null) {
                        BankDetail bankDetail = bankDetailDao.get(paymentDetailVO.getBankId());
                        paymentDetails.setBankDetail(bankDetail);
                    } else {
                        paymentDetails.setBankDetail(null);
                    }

                    if (paymentDetailVO.getTypeCode().equals("CQ")) {
                        ChequePaymentDetail chequePaymentDetail = new ChequePaymentDetail();
                        CreditAndDebitAccount creditAndDebitAccount = new CreditAndDebitAccount();

                        BankDetail bankDetail = bankDetailDao.get(paymentDetailVO.getBankId());
                        chequePaymentDetail.setCreatedAt(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                        chequePaymentDetail.setCheque_status(ChequeStatus.PENDING);
                        chequePaymentDetail.setChequeNumber(paymentDetailVO.getChequeNumber());
                        chequePaymentDetail.setChequeDate(commonFunction.getDateTimeByDateString(paymentDetailVO.getChequeDate()));
                        chequePaymentDetail.setBankDetail(bankDetail);
                        chequePaymentDetail.setDescription(paymentDetailVO.getDescription());

                        Long chequeDetailId = chequePaymentDetailDao.save(chequePaymentDetail);
                        ChequePaymentDetail saveChequePaymentDetail = chequePaymentDetailDao.get(chequeDetailId);
                        paymentDetails.setChequePaymentDetail(saveChequePaymentDetail);


                        creditAndDebitAccount.setCreatedAt(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                        creditAndDebitAccount.setCredit(paymentDetailVO.getAmount());
                        creditAndDebitAccount.setPaymentDescription(paymentDetailVO.getDescription());
                        creditAndDebitAccount.setChequePaymentDetail(saveChequePaymentDetail);
                        creditAndDebitAccount.setGoodReceived(insertedGoodReceivedObject);
                        creditAndDebitAccount.setPaymentMethod(paymentMethod);
                        creditAndDebitAccount.setCashInOut(CashInOut.CashOut);
                        creditAndDebitAccountDao.save(creditAndDebitAccount);


                    }
                    if (paymentDetailVO.getTypeCode().equals("CH")) {
                        CreditAndDebitAccount creditAndDebitAccount = new CreditAndDebitAccount();
                        creditAndDebitAccount.setCreatedAt(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                        creditAndDebitAccount.setCredit(paymentDetailVO.getAmount());
                        creditAndDebitAccount.setPaymentDescription(paymentDetailVO.getDescription());
                        creditAndDebitAccount.setGoodReceived(insertedGoodReceivedObject);
                        creditAndDebitAccount.setPaymentMethod(paymentMethod);
                        creditAndDebitAccount.setCashInOut(CashInOut.CashOut);
                        creditAndDebitAccountDao.save(creditAndDebitAccount);

                    }

                    if (paymentDetailVO.getTypeCode().equals("CR")) {

                        Creditor creditor = new Creditor();
                        creditor.setCreatedAt(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                        creditor.setCredit(paymentDetailVO.getAmount());
                        creditor.setGoodReceived(insertedGoodReceivedObject);
                        creditor.setDescription(paymentDetailVO.getDescription());
                        creditor.setPaymentDate(commonFunction.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                        creditor.setSupplier(insertedGoodReceivedObject.getPurchaseOrder().getSupplier());
                        
                        creditorDao.save(creditor);

                    }

                    paymentDetails.setPaymentMethod(paymentMethod);

                    paymentDetailDao.save(paymentDetails);

                }
            }


        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}
