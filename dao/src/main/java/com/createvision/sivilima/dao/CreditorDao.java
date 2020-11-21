package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.Creditor;

import java.util.List;

public interface CreditorDao extends AbstractDao<Creditor, Long>  {
    List<Creditor> getSupplierListById(Long id) throws Exception;
}
