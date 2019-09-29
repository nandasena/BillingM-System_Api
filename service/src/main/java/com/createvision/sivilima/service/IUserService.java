package com.createvision.sivilima.service;

import com.createvision.sivilima.tableModel.User;

public interface IUserService {
    User getUserByUserName(String userName) throws Exception;
}
