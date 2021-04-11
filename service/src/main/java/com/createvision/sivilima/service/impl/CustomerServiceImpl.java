package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.BranchDao;
import com.createvision.sivilima.dao.CustomerDao;
import com.createvision.sivilima.service.CustomerService;
import com.createvision.sivilima.tableModel.Branch;
import com.createvision.sivilima.tableModel.Customer;
import com.createvision.sivilima.tableModel.Supplier;
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

    @Autowired
    BranchDao branchDao;

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
                    customerSupplierVO.setAddress1(tem.getAddress1());
                    customerSupplierVO.setAddress2(tem.getAddress2());
                    customerSupplierVO.setContactNumber(tem.getTelephoneNo());

                    customerSupplierVOList.add(customerSupplierVO);
                }
            }

        } catch (Exception e) {
            throw e;
        }
        return customerSupplierVOList;
    }


    @Override
    public Boolean createCustomer(List<CustomerSupplierVO> customerSupplierVOs) throws Exception {
        try {
            if(!customerSupplierVOs.isEmpty()){
                for (CustomerSupplierVO customerVO:customerSupplierVOs) {
                    Branch branch = branchDao.get(customerVO.getBranchId());
                    Customer customer =new Customer();
                    customer.setAddress1(customerVO.getAddress1());
                    customer.setAddress2(customerVO.getAddress2());
                    customer.setAddress3(customerVO.getAddress3());
                    customer.setFirstName(customerVO.getFirstName());
                    customer.setLastName(customerVO.getLastName());
                    customer.setEmail(customerVO.getEmail());
                    customer.setNIC(customerVO.getNIC());
                    customer.setRemark(customerVO.getRemark());
                    customer.setTelephoneNo(customerVO.getContactNumber());
                    customer.setBranch(branch);

                    customerDao.save(customer);
                }
            }

            return true;
        }catch (Exception e){
            throw e;
        }

    }
}
