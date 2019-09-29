package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.User;


public interface IUserDao extends AbstractDao < User, Long > {

    User getUserByUsername(String username) throws Exception;
}
