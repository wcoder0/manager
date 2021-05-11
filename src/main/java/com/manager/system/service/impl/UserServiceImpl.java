package com.manager.system.service.impl;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manager.system.entity.User;
import com.manager.system.mapper.UserMapper;
import com.manager.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannibal
 * @since 2021-05-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

   @Autowired
   private UserMapper userMapper;

   @Override
   public User selectUserByUserPass(String name, String pass) {
      QueryWrapper queryWrapper = new QueryWrapper();
      queryWrapper.eq("name", name);
      queryWrapper.eq("pass", pass);
      return userMapper.selectOne(queryWrapper);
   }


}
