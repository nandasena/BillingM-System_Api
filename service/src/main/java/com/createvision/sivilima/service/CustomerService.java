package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.CustomerVO;

import java.util.List;

public interface CustomerService {

    List<CustomerVO> getAllCustomer() throws Exception;
}
