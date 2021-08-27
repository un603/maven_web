package com.hit.service;

import com.hit.pojo.User;

public interface UserService {
    /**
     * @Author DELL
     * @Description  注册用户
     * @Date 2021/7/22 15:24
     * @Param [user]
     * @Return void
     * @Version 1.0
     */
    void registerUser(User user);

    /**
     * @Author DELL
     * @Description  登录
     * @Date 2021/7/22 15:24
     * @Param [user]
     * @Return void 如果返回 null，说明登录失败，返回有值，是登录成功
     * @Version 1.0
     */
    User login(User user);

    /**
     * @Author DELL
     * @Description 检查 用户名是否可用
     * @Date 2021/7/22 15:27
     * @Param [username]
     * @Return boolean 返回 true 表示用户名已存在，返回 false 表示用户名可用
     * @Version 1.0
     */
    boolean existsUsername(String username);
}
