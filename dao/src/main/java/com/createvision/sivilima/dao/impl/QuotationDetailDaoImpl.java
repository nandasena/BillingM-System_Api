package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.QuotationDetailsDao;
import com.createvision.sivilima.tableModel.QuotationDetails;
import org.springframework.stereotype.Repository;

@Repository("QuotationDetailDao")
public class QuotationDetailDaoImpl extends AbstractDaoImpl<QuotationDetails,Long> implements QuotationDetailsDao {
}
