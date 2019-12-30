package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.CreditAndDebitAccountService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("creditAndDebitAccountService")
@Transactional
public class CreditAndDebitAccountServiceImpl implements CreditAndDebitAccountService {

    @Autowired
    CreditAndDebitAccountDao creditAndDebitAccountDao;

    @Autowired
    PaymentMethodDao paymentMethodDao;

    @Autowired
    CommonFunctionsImpl commonFunctions;

    @Autowired
    ChequePaymentDetailDao chequePaymentDetailDao;

    @Autowired
    BankDetailDao bankDetailDao;

    @Autowired
    SupplierDao supplierDao;

    @Autowired
    CustomerDao customerDao;

    @Override
    public PaymentDetailVO createNewPayment(PaymentDetailVO paymentDetailVO) throws Exception {

        try {
            PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodByTypeCode(paymentDetailVO.getTypeCode());
            ChequePaymentDetail chequePaymentDetail = new ChequePaymentDetail();
            CreditAndDebitAccount creditAndDebitAccount = new CreditAndDebitAccount();

            if (paymentMethod.getId() == 5) {

                chequePaymentDetail.setChequeNumber(paymentDetailVO.getChequeNumber());
                chequePaymentDetail.setClearingDate(commonFunctions.getDateTimeByDateString(paymentDetailVO.getChequeDate()));
                chequePaymentDetail.setDescription(paymentDetailVO.getDescription());
                chequePaymentDetail.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                BankDetail bankDetail = bankDetailDao.get(paymentDetailVO.getBankId());
                creditAndDebitAccount.setBankDetail(bankDetail);
                Long id = chequePaymentDetailDao.save(chequePaymentDetail);
                chequePaymentDetail = chequePaymentDetailDao.get(id);
                creditAndDebitAccount.setChequePaymentDetail(chequePaymentDetail);

            } else if (paymentMethod.getId() == 6) {

                BankDetail bankDetail = bankDetailDao.get(paymentDetailVO.getBankId());
                creditAndDebitAccount.setBankDetail(bankDetail);
            }

            if (paymentDetailVO.getSupplierId() != null) {
                Supplier supplier = supplierDao.get(paymentDetailVO.getSupplierId());
                creditAndDebitAccount.setSupplier(supplier);
            }
            if (paymentDetailVO.getCustomerId() != null) {
                Customer customer = customerDao.get(paymentDetailVO.getCustomerId());
                creditAndDebitAccount.setCustomer(customer);
            }
            if (paymentDetailVO.getIncomeOrCost() == 1) {
                creditAndDebitAccount.setDebit(paymentDetailVO.getPaidAmount());
            }
            if (paymentDetailVO.getIncomeOrCost() == 2) {
                creditAndDebitAccount.setCredit(paymentDetailVO.getPaidAmount());
            }
            creditAndDebitAccount.setPaymentMethod(paymentMethod);
            creditAndDebitAccount.setPaymentDescription(paymentDetailVO.getDescription());
            creditAndDebitAccount.setCredit(paymentDetailVO.getPaidAmount());
            creditAndDebitAccount.setIncomeOrCost(commonFunctions.getPaymentType(paymentDetailVO.getIncomeOrCost()).name);


        } catch (Exception e) {


        }
        return null;
    }
}
