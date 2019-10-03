package com.createvision.sivilima.service;

import com.createvision.sivilima.tableModel.User;
import com.createvision.sivilima.valuesObject.UserVO;

public interface IUserService {
    User getUserByUserName(String userName) throws Exception;
    UserVO createNewUser(UserVO userVO) throws Exception;
}
