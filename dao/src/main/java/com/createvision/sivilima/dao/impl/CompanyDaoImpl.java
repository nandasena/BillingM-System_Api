package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.tableModel.CompanyDetail;
import com.createvision.sivilima.dao.CompanyDao;
import org.springframework.stereotype.Repository;

@Repository("companyDao")
public class CompanyDaoImpl extends AbstractDaoImpl<CompanyDetail,Long> implements CompanyDao {
}
