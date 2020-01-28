package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.CustomerSupplierVO;

import java.util.List;

public interface SupplierService {
    List<CustomerSupplierVO>getAllSupplier() throws Exception;
    Boolean createSupplier(List<CustomerSupplierVO> customerSupplierVOS) throws Exception;
}
