package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.CustomerDao;
import com.createvision.sivilima.service.CustomerService;
import com.createvision.sivilima.tableModel.Customer;
import com.createvision.sivilima.valuesObject.CustomerSupplierVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public List<CustomerSupplierVO> getAllCustomer() throws Exception {
        List<CustomerSupplierVO> customerSupplierVOList = new ArrayList<>();
        try {
            List<Customer> customerList = customerDao.getAll();
            if (!customerList.isEmpty()) {

                for (Customer tem : customerList) {
                    CustomerSupplierVO customerSupplierVO = new CustomerSupplierVO();
                    customerSupplierVO.setCustomerId(tem.getId());
                    customerSupplierVO.setFirstName(tem.getFirstName());
                    customerSupplierVO.setLastName(tem.getLastName());

                    customerSupplierVOList.add(customerSupplierVO);
                }
            }

        } catch (Exception e) {
            throw e;
        }
        return customerSupplierVOList;
    }
}
