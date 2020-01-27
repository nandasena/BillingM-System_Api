package com.createvision.sivilima.service.impl;


import com.createvision.sivilima.dao.SupplierDao;
import com.createvision.sivilima.service.SupplierService;
import com.createvision.sivilima.tableModel.Supplier;
import com.createvision.sivilima.valuesObject.CustomerSupplierVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("supplierService")
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierDao supplierDao;

    @Override
    public List<CustomerSupplierVO> getAllSupplier() throws Exception {
        try {
            List<CustomerSupplierVO> supplierVOList = new ArrayList<>();
            List<Supplier> supplierList = supplierDao.getAll();
            if (!supplierList.isEmpty()) {
                for (Supplier supplier : supplierList) {
                    CustomerSupplierVO supplierObject =new CustomerSupplierVO();
                    supplierObject.setSupplierId(supplier.getId());
                    supplierObject.setFirstName(supplier.getFirstName());
                    supplierObject.setLastName(supplier.getLastName());

                    supplierVOList.add(supplierObject);

                }
            }

            return supplierVOList;

        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public CustomerSupplierVO createSupplier(List<CustomerSupplierVO> customerSupplierVOS) throws Exception {
        try {
            if(!customerSupplierVOS.isEmpty()){
                for (CustomerSupplierVO supplierVo:customerSupplierVOS) {
                    Supplier supplier =new Supplier();
                    supplier.setAddress1(supplierVo.getAddress1());
                    supplier.setAddress2(supplierVo.getAddress2());
                    supplier.setAddress3(supplierVo.getAddress3());
                    supplier.setFirstName(supplierVo.getFirstName());
                    supplier.setLastName(supplierVo.getLastName());
                    supplier.setEmail(supplierVo.getEmail());
                    supplier.setNIC(supplierVo.getNIC());
                    supplier.setRemark(supplierVo.getRemark());

                    supplierDao.save(supplier);
                }
            }


            return null;
        }catch (Exception e){
            throw e;
        }

    }
}
