package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.BranchDao;
import com.createvision.sivilima.tableModel.Branch;
import org.springframework.stereotype.Repository;

@Repository("branchDao")
public class BranchDaoImpl extends AbstractDaoImpl<Branch,Long> implements BranchDao {
}
