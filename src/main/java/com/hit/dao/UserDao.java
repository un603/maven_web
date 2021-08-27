package com.hit.dao;

import com.hit.pojo.User;

public interface UserDao {
    /**
     * @Author DELL
     * @Description 根据用户名查询用户信息
     * @Date 2021/7/22 14:53
     * @Param [username]
     * @Return com.hit.pojo.User
     * @Version 1.0
     */
    public User queryUserByUsername(String username);

    /**
     * @Author DELL
     * @Description 根据 用户名和密码查询用户信息
     * @Date 2021/7/22 14:53
     * @Param [username, password]
     * @Return com.hit.pojo.User
     * @Version 1.0
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * @Author DELL
     * @Description 保存用户信息
     * @Date 2021/7/22 14:53
     * @Param [user]
     * @Return int
     * @Version 1.0
     */
    public int saveUser(User user);
}
