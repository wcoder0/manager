package com.manager.system.controller;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manager.system.entity.User;
import com.manager.system.model.ResponseModel;
import com.manager.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

   @Autowired
   private UserService userService;

   @PostMapping("/login")
   public ResponseModel login(@RequestBody User userLogin, HttpServletRequest request) {
      ResponseModel responseModel = new ResponseModel();
      String name = userLogin.getName();
      String pass = userLogin.getPassword();
      pass = SecureUtil.md5(pass);
      QueryWrapper queryWrapper = new QueryWrapper();
      queryWrapper.eq("name", name);
      queryWrapper.eq("password", pass);
      User user = userService.getOne(queryWrapper);

      if(user == null) {
         responseModel.setSuccess(false);
         responseModel.setMessage("用户名或者密码错误");
      }
      else {
         responseModel.setSuccess(true);
         request.getSession().setAttribute("loginUser", userLogin);
      }

      return responseModel;
   }

}
