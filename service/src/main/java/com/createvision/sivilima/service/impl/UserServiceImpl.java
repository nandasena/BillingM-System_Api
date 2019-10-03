package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.CompanyDao;
import com.createvision.sivilima.dao.IRoleDao;
import com.createvision.sivilima.dao.IUserDao;
import com.createvision.sivilima.service.IUserService;
import com.createvision.sivilima.tableModel.CompanyDetail;
import com.createvision.sivilima.tableModel.User;
import com.createvision.sivilima.tableModel.UserRole;
import com.createvision.sivilima.valuesObject.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.management.relation.Role;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    IRoleDao iRoleDao;

    @Override
    public User getUserByUserName(String userName) throws Exception {
        User user =userDao.getUserByUsername(userName);

        return user;
    }

    @Override
    public UserVO createNewUser(UserVO userVO) throws Exception {
            User insertedUser =new User();
        try {
            User user =userDao.getUserByUsername(userVO.getUserName());
            CompanyDetail companyDetail =companyDao.get((long)1);
            UserRole role = iRoleDao.get((long)1);

            if(user==null){
             User newUser =new User();
             newUser.setDelete(false);
             newUser.setName(userVO.getName());
             newUser.setUserName(userVO.getUserName());
             newUser.setPassword(bCryptPasswordEncoder.encode(userVO.getPassword()));
             newUser.setCompanyDetail(companyDetail);
             newUser.setUserRoleId(role);
             Long id =userDao.save(newUser);
             userVO.setUserId(id);


            }else {
             return null;
            }


        }catch (Exception e){
            throw e;
        }
        return userVO;
    }
}
