package com.shf.app40_content_provider.dao;

import com.shf.app40_content_provider.pojo.User;

import java.util.List;

/**
 * 数据库接口层
 */
public interface IUserDao {
    /**
     * 添加用户
     * @param user
     * @return
     */
    long addUser(User user);


    /**
     * 删除用户
     * @param id
     * @return
     */
    int delUserById(int id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查询用户记录
     * @param id
     * @return
     */
    User getUserById(int id);


    /**
     * 获取所有的用户记录
     * @return
     */
    List<User> listAllUser();
}
