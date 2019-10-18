package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.CustomerDao;
import com.createvision.sivilima.tableModel.Customer;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDaoImpl<Customer, Long> implements CustomerDao {
}
