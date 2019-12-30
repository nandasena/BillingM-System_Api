package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.CreditAndDebitAccountDao;
import com.createvision.sivilima.tableModel.CreditAndDebitAccount;
import org.springframework.stereotype.Repository;

@Repository("creditAndDebitAccountDao")
public class CreditAndDebitAccountDaoImpl extends AbstractDaoImpl<CreditAndDebitAccount, Long> implements CreditAndDebitAccountDao {
}
