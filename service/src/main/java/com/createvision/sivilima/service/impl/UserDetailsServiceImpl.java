package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.IUserDao;
import com.createvision.sivilima.tableModel.User;
import com.createvision.sivilima.tableModel.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Repository("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=null;
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        try {
            user = userDao.getUserByUsername(username);
            for (UserRole role : user.getUserRole()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }

        }catch (Exception e){

        }
        if(user!=null){
            return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),grantedAuthorities);
        }else {
            return new org.springframework.security.core.userdetails.User("error","error",grantedAuthorities);
        }

    }
}
