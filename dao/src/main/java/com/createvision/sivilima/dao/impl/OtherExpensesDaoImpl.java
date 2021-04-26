package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.OtherExpensesDao;
import com.createvision.sivilima.tableModel.OtherExpenses;
import org.springframework.stereotype.Repository;

@Repository("otherExpensesDao")
public class OtherExpensesDaoImpl extends AbstractDaoImpl<OtherExpenses,Long> implements OtherExpensesDao {
}
