package com.manager.system.service;

import com.manager.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hannibal
 * @since 2021-05-09
 */
public interface UserService extends IService<User> {

   public User selectUserByUserPass(String name,String pass);

}
