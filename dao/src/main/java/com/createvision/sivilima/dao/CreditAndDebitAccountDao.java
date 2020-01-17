package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.CreditAndDebitAccount;

public interface CreditAndDebitAccountDao extends AbstractDao<CreditAndDebitAccount, Long>   {

    CreditAndDebitAccount getPaymentDetailByDebtorId(Long id) throws Exception;
}
