package com.createvision.sivilima.service.impl;


import com.createvision.sivilima.dao.SupplierDao;
import com.createvision.sivilima.service.SupplierService;
import com.createvision.sivilima.tableModel.Supplier;
import com.createvision.sivilima.valuesObject.CustomerVO;
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
    public List<CustomerVO> getAllSupplier() throws Exception {
        try {
            List<CustomerVO> supplierVOList = new ArrayList<>();
            List<Supplier> supplierList = supplierDao.getAll();
            if (!supplierList.isEmpty()) {
                for (Supplier supplier : supplierList) {
                    CustomerVO supplierObject =new CustomerVO();
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
}
