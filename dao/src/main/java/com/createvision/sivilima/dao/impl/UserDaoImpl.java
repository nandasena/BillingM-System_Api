package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.IUserDao;
import com.createvision.sivilima.tableModel.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("IUserDao")
public class UserDaoImpl extends AbstractDaoImpl<User,Long> implements IUserDao {
    @Override
    public User getUserByUsername(String username) throws Exception {
        Criteria criteria= getSession().createCriteria(User.class,"user");
        criteria.add(Restrictions.eq("user.userName",username));
        criteria.setMaxResults(1);
        User user =(User) criteria.uniqueResult();
        return user;
    }
}
