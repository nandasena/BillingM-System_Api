package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.CustomerDao;
import com.createvision.sivilima.service.CustomerService;
import com.createvision.sivilima.tableModel.Customer;
import com.createvision.sivilima.valuesObject.CustomerVO;
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
    public List<CustomerVO> getAllCustomer() throws Exception {
        List<CustomerVO> customerVOList = new ArrayList<>();
        try {
            List<Customer> customerList = customerDao.getAll();
            if (!customerList.isEmpty()) {

                for (Customer tem : customerList) {
                    CustomerVO customerVO = new CustomerVO();
                    customerVO.setCustomerId(tem.getId());
                    customerVO.setFirstName(tem.getFirstName());
                    customerVO.setLastName(tem.getLastName());

                    customerVOList.add(customerVO);
                }
            }

        } catch (Exception e) {
            throw e;
        }
        return customerVOList;
    }
}
