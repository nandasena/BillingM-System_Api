package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.BrandDao;
import com.createvision.sivilima.tableModel.Brand;
import org.springframework.stereotype.Repository;

@Repository("branchDao")
public class BrandDaoImpl extends AbstractDaoImpl<Brand,Long> implements BrandDao {
}
