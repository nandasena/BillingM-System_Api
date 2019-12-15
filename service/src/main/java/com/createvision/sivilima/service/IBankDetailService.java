package com.createvision.sivilima.service;

import com.createvision.sivilima.valuesObject.BankModalVO;

import java.util.List;

public interface IBankDetailService  {

    List<BankModalVO> getAllBank()throws Exception;

}
