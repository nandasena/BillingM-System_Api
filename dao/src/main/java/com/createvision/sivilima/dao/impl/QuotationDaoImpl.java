package com.createvision.sivilima.dao.impl;


import com.createvision.sivilima.dao.QuotationDao;
import com.createvision.sivilima.tableModel.Quotation;
import org.springframework.stereotype.Repository;

@Repository("QuotationDao")
public class QuotationDaoImpl extends AbstractDaoImpl<Quotation,Long> implements QuotationDao {
}
