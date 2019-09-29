package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.IUserDao;
import com.createvision.sivilima.service.IUserService;
import com.createvision.sivilima.tableModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User getUserByUserName(String userName) throws Exception {
        User user =userDao.getUserByUsername(userName);

        return user;
    }
}
