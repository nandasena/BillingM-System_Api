package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.BankDetailDao;
import com.createvision.sivilima.dao.InvoiceDao;
import com.createvision.sivilima.service.IBankDetailService;
import com.createvision.sivilima.tableModel.BankDetail;
import com.createvision.sivilima.valuesObject.BankModalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("iBankDetailService")
@Transactional
public class BankDetailServiceImpl implements IBankDetailService {
    @Autowired
    BankDetailDao bankDetailDao;

    @Autowired
    InvoiceDao invoiceDao;

    @Override
    public List<BankModalVO> getAllBank() throws Exception {
        List<BankModalVO> bankModalVOList = new ArrayList<>();
        try {

            List<BankDetail> bankDetails = bankDetailDao.getAll();
            for (BankDetail bankDetail : bankDetails) {
                BankModalVO bankModalVO = new BankModalVO();
                bankModalVO.setBankId(bankDetail.getId());
                bankModalVO.setBankName(bankDetail.getBankName());
                bankModalVOList.add(bankModalVO);
            }

        } catch (Exception e) {

        }
        return bankModalVOList;
    }
}
