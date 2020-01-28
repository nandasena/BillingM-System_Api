package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.CustomerSupplierVO;

import java.util.List;

public interface CustomerService {

    List<CustomerSupplierVO> getAllCustomer() throws Exception;
    Boolean createCustomer(List<CustomerSupplierVO> customerSupplierVOs)throws Exception;
}
