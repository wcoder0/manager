package com.manager.system.controller;


import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manager.system.entity.*;
import com.manager.system.exception.BusinessException;
import com.manager.system.model.ResponseModel;
import com.manager.system.service.OwnerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hannibal
 * @since 2021-05-19
 */
@RestController
@RequestMapping("/owner")
public class OwnerController {

   @Autowired
   private OwnerService ownerService;

   @Value("${owner.image.file}")
   private String filePath;

   @GetMapping("/findOwners")
   public ResponseModel<Page<Owner>> finOwners(Owner owner, Page<Owner> page) {
      QueryWrapper<Owner> query = new QueryWrapper<>();
      ResponseModel responseModel = new ResponseModel();

      if(owner != null && StringUtils.isNotBlank(owner.getName())) {
         query.like("name", owner.getName());
      }

      if(owner != null && StringUtils.isNotBlank(owner.getAddress())) {
         query.like("address", owner.getAddress());
      }

      try {
         page = ownerService.page(page, query);
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


   @RequestMapping(value = "/addOwner", method = RequestMethod.POST, consumes = {"multipart/form-data"})
   @ResponseBody
   public ResponseModel<Owner> addOwner(@ModelAttribute OwnerVo owner, @RequestPart("picture") MultipartFile picture) throws URISyntaxException {
      ResponseModel responseModel = new ResponseModel();
      //Owner owner = owner1 == null ? owner2 : owner1;
      try {
         Owner dest = new Owner();

         if(picture != null) {
            int lastIndexOf = picture.getOriginalFilename().lastIndexOf(".");
            String fileName = picture.getOriginalFilename().substring(lastIndexOf);
            String image = owner.getIdCard() + fileName;
            File file = new File(filePath + File.separator + owner.getIdCard() + fileName);
            file.deleteOnExit();
            picture.transferTo(file);
            dest.setImage(image);
         }

         String pass = owner.getPassword();
         pass = SecureUtil.md5(pass);
         owner.setPassword(pass);
         BeanUtils.copyProperties(owner, dest);
         dest.setCreateTime(new Date());
         //dest.setPicture(picture.getBytes());
         dest.setPath(filePath);
         ownerService.save(dest);
         responseModel.setSuccess(true);
         responseModel.setData(owner);
      }
      catch(Exception e) {
         responseModel.setSuccess(false);
         responseModel.setMessage("修改用户失败");
         e.printStackTrace();
      }

      return responseModel;
   }

   @RequestMapping(value = "/updateOwner", method = RequestMethod.POST, consumes = {"multipart/form-data"})
   @ResponseBody
   public ResponseModel<Owner> updateOwner(@ModelAttribute OwnerVo owner,
                                           @RequestPart("picture") MultipartFile picture) throws Exception {
      ResponseModel responseModel = new ResponseModel();

      if(owner.getIdCard() == null) {
         throw new BusinessException("用户id不能为空");
      }

      Owner updateOwner = ownerService.getById(owner.getIdCard());

      if(updateOwner == null) {
         throw new BusinessException("更新用户失败,用户不存在");
      }

      if(picture != null) {
         int lastIndexOf = picture.getOriginalFilename().lastIndexOf(".");
         String fileName = picture.getOriginalFilename().substring(lastIndexOf);
         String image = owner.getIdCard() + fileName;
         File file = new File(filePath + File.pathSeparator + owner.getIdCard() + fileName);
         file.deleteOnExit();
         picture.transferTo(file);
         updateOwner.setImage(image);
      }

      updateOwner.setPath(filePath);
      updateOwner.setUpdateTime(new Date());
      updateOwner.setName(owner.getName());
      updateOwner.setAge(owner.getAge());
      updateOwner.setAddress(owner.getAddress());
      updateOwner.setSex(owner.getSex());
      updateOwner.setPicture(picture.getBytes());
      ownerService.updateById(updateOwner);
      responseModel.setSuccess(true);
      return responseModel;
   }

   @GetMapping("/findOwner/{id}")
   public ResponseModel<Owner> findOwner(@PathVariable("id") String id) {
      ResponseModel responseModel = new ResponseModel();

      if(null == id) {
         throw new BusinessException("入参为空");
      }

      Owner Owner = ownerService.getById(id);
      responseModel.setSuccess(true);
      responseModel.setData(Owner);
      return responseModel;
   }

   @DeleteMapping("/deleteOwner/{id}")
   public ResponseModel<String> deleteOwnerById(@PathVariable String id) {
      ResponseModel responseModel = new ResponseModel();

      if(StringUtils.isBlank(id)) {
         throw new BusinessException("用户id为空");
      }

      ownerService.removeById(id);
      responseModel.setSuccess(true);
      return responseModel;
   }

   // 批量删除
   @DeleteMapping("/deleteOwnerByIds")
   public ResponseModel<String> deleteOwnerByIds(@RequestBody String[] ids) {
      ResponseModel responseModel = new ResponseModel();

      if(null == ids) {
         throw new BusinessException("入参为空");
      }

      ownerService.removeByIds(Arrays.asList(ids));
      responseModel.setSuccess(true);
      return responseModel;
   }


   @ResponseBody
   @GetMapping(value = "getImage/{idCard}", produces = MediaType.IMAGE_JPEG_VALUE)
   public byte[] getImage(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable("idCard") String idCard) {
      Owner owner = ownerService.getById(idCard);
      FileInputStream inputStream = null;

      try {
         if(owner != null) {
            String filePath = owner.getPath() + File.separator + owner.getImage();
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            response.setContentLength(inputStream.available());
            inputStream.read(bytes);
            return bytes;
         }
      }
      catch(IOException e) {
         e.printStackTrace();
      }
      finally {
         IoUtil.close(inputStream);
      }

      byte[] bytes = new byte[]{};
      return bytes;
   }


   @PostMapping("/login")
   public ResponseModel login(@RequestBody Owner ownerLogin, HttpServletRequest request) {
      ResponseModel responseModel = new ResponseModel();
      String name = ownerLogin.getName();
      String pass = ownerLogin.getPassword();
      pass = SecureUtil.md5(pass);
      QueryWrapper queryWrapper = new QueryWrapper();
      queryWrapper.eq("name", name);
      queryWrapper.eq("password", pass);
      Owner loginOwner = ownerService.getOne(queryWrapper);

      if(loginOwner == null) {
         responseModel.setSuccess(false);
         responseModel.setMessage("用户名或者密码错误");
      }
      else {
         responseModel.setSuccess(true);
         request.getSession().setAttribute("loginOwner", loginOwner);
      }

      return responseModel;
   }

}

