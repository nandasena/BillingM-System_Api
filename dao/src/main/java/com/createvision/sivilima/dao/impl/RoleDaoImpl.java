package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.IRoleDao;
import com.createvision.sivilima.tableModel.UserRole;
import org.springframework.stereotype.Repository;

@Repository("IRoleDao")
public class RoleDaoImpl extends AbstractDaoImpl<UserRole,Long> implements IRoleDao {
}
