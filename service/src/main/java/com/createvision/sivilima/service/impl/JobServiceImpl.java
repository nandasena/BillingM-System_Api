package com.createvision.sivilima.service.impl;


import com.createvision.sivilima.dao.OtherExpensesDao;
import com.createvision.sivilima.service.JobService;
import com.createvision.sivilima.tableModel.OtherExpenses;
import com.createvision.sivilima.valuesObject.OtherExpensesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("jobService")
@Transactional
public class JobServiceImpl implements JobService {
    @Autowired
    OtherExpensesDao otherExpensesDao;

    @Override
    public List<OtherExpensesVO> getAllOtherExpenses() throws Exception {
        List<OtherExpensesVO> otherExpensesVOList = new ArrayList<>();

        try {
           List<OtherExpenses> otherExpensesList =otherExpensesDao.getAll();

            for (OtherExpenses expenses:otherExpensesList) {
                OtherExpensesVO otherExpensesVO =new OtherExpensesVO();

                otherExpensesVO.setId(expenses.getId());
                otherExpensesVO.setName(expenses.getName());
                otherExpensesVO.setDescription(expenses.getDescription());

                otherExpensesVOList.add(otherExpensesVO);

            }

        } catch (Exception e) {
            throw e;
        }
        return otherExpensesVOList;
    }
}
