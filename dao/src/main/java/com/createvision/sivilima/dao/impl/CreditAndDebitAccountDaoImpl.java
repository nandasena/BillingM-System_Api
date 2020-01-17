package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.CreditAndDebitAccountDao;
import com.createvision.sivilima.tableModel.CreditAndDebitAccount;
import com.createvision.sivilima.tableModel.Invoice;
import com.createvision.sivilima.tableModel.Item;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("creditAndDebitAccountDao")
public class CreditAndDebitAccountDaoImpl extends AbstractDaoImpl<CreditAndDebitAccount, Long> implements CreditAndDebitAccountDao {

    @Override
    public CreditAndDebitAccount getPaymentDetailByDebtorId(Long id) throws Exception {
        Criteria IinCri = getSession().createCriteria(CreditAndDebitAccount.class, "creditAndDebitAccount");
        IinCri.createAlias("creditAndDebitAccount.debtor", "debtor");
        IinCri.add(Restrictions.eq("debtor.id", id));
        IinCri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        IinCri.setMaxResults(1);
        CreditAndDebitAccount result = (CreditAndDebitAccount) IinCri.uniqueResult();

        return result;
    }
}
