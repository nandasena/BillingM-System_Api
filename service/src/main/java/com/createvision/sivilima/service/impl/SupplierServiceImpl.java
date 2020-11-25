package com.createvision.sivilima.service.impl;


import com.createvision.sivilima.dao.BranchDao;
import com.createvision.sivilima.dao.ItemCodeDao;
import com.createvision.sivilima.dao.SupplierDao;
import com.createvision.sivilima.service.SupplierService;
import com.createvision.sivilima.tableModel.Branch;
import com.createvision.sivilima.tableModel.ItemCode;
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

    @Autowired
    ItemCodeDao itemCodeDao;

    @Autowired
    BranchDao branchDao;

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
    public Boolean createSupplier(List<CustomerSupplierVO> customerSupplierVOS) throws Exception {
        try {
            if(!customerSupplierVOS.isEmpty()){
                for (CustomerSupplierVO supplierVo:customerSupplierVOS) {

                    List<ItemCode> itemCodeList = itemCodeDao.getItemCode("SUPPLIER");
                    ItemCode itemCode = itemCodeList.get(itemCodeList.size() - 1);
                    String code = itemCode.getCode();
                    int lastNUmber = itemCode.getNextNumber();
                    String lastSupplierNumber = new Integer(itemCode.getNextNumber()).toString();
                    String supplierCode = code + "_" + lastSupplierNumber;
                    itemCode.setNextNumber(++lastNUmber);

                    Branch branch = branchDao.get(supplierVo.getBranchId());

                    itemCodeDao.save(itemCode);
                    Supplier supplier =new Supplier();
                    supplier.setAddress1(supplierVo.getAddress1());
                    supplier.setAddress2(supplierVo.getAddress2());
                    supplier.setAddress3(supplierVo.getAddress3());
                    supplier.setFirstName(supplierVo.getFirstName());
                    supplier.setLastName(supplierVo.getLastName());
                    supplier.setEmail(supplierVo.getEmail());
                    supplier.setNIC(supplierVo.getNIC());
                    supplier.setRemark(supplierVo.getRemark());
                    supplier.setSupplierId(supplierCode);
                    supplier.setPhoneNumber(supplierVo.getContactNumber());
                    supplier.setBranch(branch);

                    supplierDao.save(supplier);
                }
            }
            return true;
        }catch (Exception e){
            throw e;
        }

    }
}
