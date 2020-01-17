package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.Debtor;

import java.util.List;

public interface DebtorDao extends AbstractDao<Debtor, Long>  {

    List<Debtor> getDebtorByCustomerId(Long id) throws Exception;
}
