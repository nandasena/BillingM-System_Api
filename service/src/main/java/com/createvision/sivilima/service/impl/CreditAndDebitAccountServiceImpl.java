package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.*;
import com.createvision.sivilima.service.CreditAndDebitAccountService;
import com.createvision.sivilima.tableModel.*;
import com.createvision.sivilima.valuesObject.CustomerPaymentVO;
import com.createvision.sivilima.valuesObject.PaymentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    CreditorDao creditorDao;

    @Autowired
    DebtorDao debtorDao;

    @Autowired
    PaymentDetailsOfCreditDao paymentDetailsOfCreditDao;

    @Override
    public PaymentDetailVO createNewPayment(PaymentDetailVO paymentDetailVO) throws Exception {

        try {
            PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodByTypeCode(paymentDetailVO.getTypeCode());
            ChequePaymentDetail chequePaymentDetail = new ChequePaymentDetail();
            CreditAndDebitAccount creditAndDebitAccount = new CreditAndDebitAccount();
            Creditor creditor = new Creditor();
            Debtor debtor = new Debtor();
            if (paymentMethod.getId() == 5) {

                chequePaymentDetail.setChequeNumber(paymentDetailVO.getChequeNumber());
                chequePaymentDetail.setClearingDate(commonFunctions.getDateTimeByDateString(paymentDetailVO.getChequeDate()));
                chequePaymentDetail.setDescription(paymentDetailVO.getDescription());
                chequePaymentDetail.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                BankDetail bankDetail = bankDetailDao.get(paymentDetailVO.getBankId());
                chequePaymentDetail.setBankDetail(bankDetail);
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

                creditor.setSupplier(supplier);
                creditor.setDebit(paymentDetailVO.getPaidAmount());
                creditor.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                creditor.setPaymentDate(commonFunctions.getDateTimeByDateString(paymentDetailVO.getPaymentDate()));
                creditor.setDescription(paymentDetailVO.getDescription());
                creditorDao.save(creditor);

            }
            if (paymentDetailVO.getCustomerId() != null) {
                Customer customer = customerDao.get(paymentDetailVO.getCustomerId());
                creditAndDebitAccount.setCustomer(customer);

                debtor.setCustomer(customer);
                debtor.setPaymentDate(commonFunctions.getDateTimeByDateString(paymentDetailVO.getPaymentDate()));
                debtor.setCreatedAt(commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo"));
                debtor.setDescription(paymentDetailVO.getDescription());
                debtor.setCredit(paymentDetailVO.getPaidAmount());
                debtorDao.save(debtor);

            }
            if (paymentDetailVO.getIncomeOrCost() == 1) {
                creditAndDebitAccount.setDebit(paymentDetailVO.getPaidAmount());
            }
            if (paymentDetailVO.getIncomeOrCost() == 2) {
                creditAndDebitAccount.setCredit(paymentDetailVO.getPaidAmount());
            }
            creditAndDebitAccount.setPaymentMethod(paymentMethod);
            creditAndDebitAccount.setPaymentDescription(paymentDetailVO.getDescription());
            creditAndDebitAccount.setIncomeOrCost(commonFunctions.getPaymentType(paymentDetailVO.getIncomeOrCost()).name);
            creditAndDebitAccountDao.save(creditAndDebitAccount);


        } catch (Exception e) {
            throw e;
        }
        return paymentDetailVO;
    }

    @Override
    public List<CustomerPaymentVO> getCustomerPaymentDetailById(Long id) throws Exception {
        try {
            List<CustomerPaymentVO> customerPaymentVOList = new ArrayList<>();
            List<Debtor> debtorList = debtorDao.getDebtorByCustomerId(id);
            for (Debtor debtor : debtorList) {
                CreditAndDebitAccount creditAndDebitAccount = creditAndDebitAccountDao.getPaymentDetailByDebtorId(debtor.getId());

                CustomerPaymentVO customerPaymentVO = new CustomerPaymentVO();

                customerPaymentVO.setPaymentDate(commonFunctions.convertDateToString(debtor.getPaymentDate()));
                customerPaymentVO.setDebitAmount(debtor.getDebit());
                customerPaymentVO.setCreditAmount(debtor.getCredit());
                customerPaymentVO.setInvoiceId(debtor.getInvoice() != null ? debtor.getInvoice().getId() : null);
                customerPaymentVO.setDescription(debtor.getDescription() != null ? debtor.getDescription() : "--");
                customerPaymentVO.setPaymentType(creditAndDebitAccount != null ? creditAndDebitAccount.getPaymentMethod().getTypeCode() : "--");

                customerPaymentVOList.add(customerPaymentVO);

            }
            return customerPaymentVOList;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<PaymentDetailVO> getCreditPaymentDetailsById(Long id) throws Exception {
        try {
            List<PaymentDetailVO> PaymentDetailVOList = new ArrayList<>();
            List<PaymentDetailsOfCredit> paymentDetailsOfCredits = paymentDetailsOfCreditDao.getPaymentDetailsById(id);

            for (PaymentDetailsOfCredit paymentDetailsOfCredit : paymentDetailsOfCredits) {
                PaymentDetailVO paymentDetailVO = new PaymentDetailVO();
                paymentDetailVO.setPaidAmount(paymentDetailsOfCredit.getAmount());
                paymentDetailVO.setPaymentType(paymentDetailsOfCredit.getPaymentMethod().getName());
                paymentDetailVO.setPaymentDate(commonFunctions.convertDateToString(paymentDetailsOfCredit.getPaymentDate()));
                paymentDetailVO.setChequeNumber(paymentDetailsOfCredit.getChequeNumber() == null ? "--" :
                        paymentDetailsOfCredit.getChequeNumber().isEmpty() ? "--" : paymentDetailsOfCredit.getChequeNumber());
                paymentDetailVO.setChequeDate(paymentDetailsOfCredit.getChequeDate() != null ? commonFunctions.convertDateToString(paymentDetailsOfCredit.getChequeDate()) : "--");
                paymentDetailVO.setCardNumber(paymentDetailsOfCredit.getCardNumber().isEmpty() ? "--" : paymentDetailsOfCredit.getCardNumber());
                paymentDetailVO.setBankName(paymentDetailsOfCredit.getBankDetail() != null ? paymentDetailsOfCredit.getBankDetail().getBankName() : "--");
                paymentDetailVO.setIsClear(paymentDetailsOfCredit.getClear() != null ? paymentDetailsOfCredit.getClear().toString() :"--");

                PaymentDetailVOList.add(paymentDetailVO);

            }
            return PaymentDetailVOList;
        } catch (Exception e) {
            throw e;
        }

    }
}
