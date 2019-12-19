package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.SupplierDao;
import com.createvision.sivilima.tableModel.Supplier;
import org.springframework.stereotype.Repository;

@Repository("supplierDao")
public class SupplierDaoImpl extends AbstractDaoImpl<Supplier,Long> implements SupplierDao {
}
