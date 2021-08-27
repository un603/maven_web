package com.hit.dao.impl;

import com.hit.dao.UserDao;
import com.hit.pojo.User;

/**
 * @Classname UserDaoImpl
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/22 14:55
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from t_user where username = ? ";
        return queryForOne(User.class, sql, username);

    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from t_user where username = ? and `password` = ? ";
        return queryForOne(User.class, sql, username, password);

    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,`password`,email) values(?,?,?) ";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());

    }
}
