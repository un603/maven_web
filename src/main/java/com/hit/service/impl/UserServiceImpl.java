package com.hit.service.impl;

import com.hit.dao.impl.UserDaoImpl;
import com.hit.pojo.User;
import com.hit.service.UserService;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/22 15:28
 */
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        //等于null说明没查到，没查到说明可用
        return userDao.queryUserByUsername(username) != null;
    }
}
