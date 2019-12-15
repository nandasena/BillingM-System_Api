package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.BankDetailDao;
import com.createvision.sivilima.tableModel.BankDetail;
import org.springframework.stereotype.Repository;

@Repository("bankDetailDao")
public class BankDetailDaoImpl extends AbstractDaoImpl<BankDetail,Long> implements BankDetailDao {
}
