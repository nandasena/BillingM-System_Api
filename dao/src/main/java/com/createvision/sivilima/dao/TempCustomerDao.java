package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.TempCustomer;

public interface TempCustomerDao extends AbstractDao<TempCustomer, Long>  {
    TempCustomer getCustomerDetailsById(Long id ) throws Exception;
}
