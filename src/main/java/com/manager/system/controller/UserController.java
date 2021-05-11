package com.manager.system.controller;

import java.net.URISyntaxException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manager.system.entity.User;
import com.manager.system.exception.BusinessException;
import com.manager.system.model.ResponseModel;
import com.manager.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user")
public class UserController {

   @Autowired
   private UserService userService;

   @GetMapping("/findUsers")
   public ResponseModel<Page<User>> finUsers(User user, Page<User> page) {
      QueryWrapper<User> query = new QueryWrapper<>();
      ResponseModel responseModel = new ResponseModel();

      if(user != null && StringUtils.isNotBlank(user.getName())) {
         query.like("name", user.getName());
      }

      if(user != null && StringUtils.isNotBlank(user.getUsername())) {
         query.like("username", user.getUsername());
      }

      try {
         page = userService.page(page, query);
         responseModel.setSuccess(true);
         responseModel.setCode(0);
         responseModel.setData(page);
      }
      catch(Exception e) {
         responseModel.setSuccess(false);
         responseModel.setMessage("查询用户失败");
         e.printStackTrace();
      }


      return responseModel;
   }


   @PostMapping("/addUser")
   public ResponseModel<User> addUser(@RequestBody User user) throws URISyntaxException {
      ResponseModel responseModel = new ResponseModel();
      String pass = SecureUtil.md5(user.getPassword());
      user.setPassword(pass);

      try {
         user.setCreateTime(new Date());
         userService.save(user);
         responseModel.setSuccess(true);
         responseModel.setData(user);
      }
      catch(Exception e) {
         responseModel.setSuccess(false);
         responseModel.setMessage("修改用户失败");
         e.printStackTrace();
      }

      return responseModel;
   }

   @PutMapping("/updateUser")
   public ResponseModel<User> updateUser(@RequestBody User user) {
      ResponseModel responseModel = new ResponseModel();

      if(user.getId() == null) {
         throw new BusinessException("用户id不能为空");
      }

      User updateUser = userService.getById(user.getId());

      if(updateUser == null) {
         throw new BusinessException("更新用户失败,用户不存在");
      }

      updateUser.setUpdateTime(new Date());
      updateUser.setName(user.getName());
      updateUser.setAge(user.getAge());
      updateUser.setAddress(user.getAddress());
      updateUser.setPhone(user.getPhone());
      updateUser.setSex(user.getSex());
      //String pass = SecureUtil.md5(user.getPassword());
      //updateUser.setPassword(pass);
      updateUser.setType(user.getType());
      updateUser.setUsername(user.getUsername());
      updateUser.setStatus(user.getStatus());
      updateUser.setEmail(user.getEmail());
      userService.updateById(updateUser);
      responseModel.setSuccess(true);
      return responseModel;
   }

   @GetMapping("/findUser/{id}")
   public ResponseModel<User> findUser(@PathVariable("id") String id) {
      ResponseModel responseModel = new ResponseModel();

      if(null == id) {
         throw new BusinessException("入参为空");
      }

      User user = userService.getById(id);
      responseModel.setSuccess(true);
      responseModel.setData(user);
      return responseModel;
   }

   @DeleteMapping("/deleteUser/{id}")
   public ResponseModel<String> deleteUserById(@PathVariable String id) {
      ResponseModel responseModel = new ResponseModel();

      if(StringUtils.isBlank(id)) {
         throw new BusinessException("用户id为空");
      }

      User user = userService.getById(id);

      if(user != null && "admin".equalsIgnoreCase(user.getName())) {
         throw new BusinessException("admin不能删除");
      }

      userService.removeById(id);
      responseModel.setSuccess(true);
      return responseModel;
   }

   // 批量删除
   @DeleteMapping("/deleteUserByIds")
   public ResponseModel<String> deleteUserByIds(@RequestBody String[] ids) {
      ResponseModel responseModel = new ResponseModel();

      if(null == ids) {
         throw new BusinessException("入参为空");
      }

      userService.removeByIds(Arrays.asList(ids));
      responseModel.setSuccess(true);
      return responseModel;
   }

   @GetMapping("/getLoginUser")
   public ResponseModel<User> getLoginUser(HttpServletRequest httpServletRequest) {
      ResponseModel responseModel = new ResponseModel();
      Object loginUser = httpServletRequest.getSession().getAttribute("loginUser");

      if(loginUser == null) {
         responseModel.setSuccess(false);
      }
      else {
         responseModel.setSuccess(true);
      }

      responseModel.setData(loginUser);
      return responseModel;
   }
}

