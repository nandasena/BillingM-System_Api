package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.TempCustomerDao;
import com.createvision.sivilima.tableModel.TempCustomer;
import org.springframework.stereotype.Repository;

@Repository("tempCustomerDao")
public class TempCustomerDaoImpl extends AbstractDaoImpl<TempCustomer, Long> implements TempCustomerDao {

}
