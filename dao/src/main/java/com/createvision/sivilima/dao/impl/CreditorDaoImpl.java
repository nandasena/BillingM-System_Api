package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.CreditorDao;
import com.createvision.sivilima.tableModel.Creditor;
import org.springframework.stereotype.Repository;

@Repository("creditorDao")
public class CreditorDaoImpl extends AbstractDaoImpl<Creditor, Long> implements CreditorDao {
}
