package com.createvision.sivilima.service;

import com.createvision.sivilima.tableModel.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ISecurityService {

    void autologin(String username, String password) throws UsernameNotFoundException, AuthenticationException, Exception;
    User fetchLoggedInUser() throws Exception;
}
