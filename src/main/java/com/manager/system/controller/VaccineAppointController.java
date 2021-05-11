package com.manager.system.controller;


import java.net.URISyntaxException;
import java.util.*;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manager.system.entity.VaccineAppoint;
import com.manager.system.entity.VaccineAppoint;
import com.manager.system.exception.BusinessException;
import com.manager.system.model.ResponseModel;
import com.manager.system.service.VaccineAppointService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;


@RestController
@RequestMapping("/vaccineAppoint")
public class VaccineAppointController {

   @Autowired
   private VaccineAppointService vaccineAppointService;

   @GetMapping("/findVaccineAppoints")
   public ResponseModel<Page<VaccineAppoint>> finVaccineAppoints(VaccineAppoint vaccineAppoint,
                                                                 Page<VaccineAppoint> page) {
      QueryWrapper<VaccineAppoint> query = new QueryWrapper<>();
      ResponseModel responseModel = new ResponseModel();

      if(vaccineAppoint != null && StringUtils.isNotBlank(vaccineAppoint.getName())) {
         query.like("name", vaccineAppoint.getName());
      }

      if(vaccineAppoint != null && StringUtils.isNotBlank(vaccineAppoint.getMeetCondition())) {
         query.eq("meet_condition", vaccineAppoint.getMeetCondition());
      }

      try {
         page = vaccineAppointService.page(page, query);
         responseModel.setSuccess(true);
         responseModel.setCode(0);
         responseModel.setData(page);
      }
      catch(Exception e) {
         responseModel.setSuccess(false);
         responseModel.setCode(-1);
         responseModel.setMessage("查询疫情填报失败");
         e.printStackTrace();
      }

      return responseModel;
   }


   @PostMapping("/addVaccineAppoint")
   public ResponseModel<VaccineAppoint> addVaccineAppoint(@RequestBody VaccineAppoint vaccineAppoint) throws URISyntaxException {
      ResponseModel responseModel = new ResponseModel();

      try {
         vaccineAppoint.setCreateTime(new Date());
         vaccineAppointService.save(vaccineAppoint);
         responseModel.setSuccess(true);
         responseModel.setData(vaccineAppoint);
      }
      catch(Exception e) {
         responseModel.setSuccess(false);
         responseModel.setMessage("修改疫情填报失败");
         e.printStackTrace();
      }

      return responseModel;
   }

   @PutMapping("/updateVaccineAppoint")
   public ResponseModel<VaccineAppoint> updateVaccineAppoint(@RequestBody VaccineAppoint VaccineAppoint) {
      ResponseModel responseModel = new ResponseModel();

      if(VaccineAppoint.getId() == null) {
         throw new BusinessException("填报id不能为空");
      }

      VaccineAppoint updateVaccineAppoint = vaccineAppointService.getById(VaccineAppoint.getId());

      if(updateVaccineAppoint == null) {
         throw new BusinessException("更新填报失败,疫情填报不存在");
      }

      updateVaccineAppoint.setUpdateTime(new Date());
      updateVaccineAppoint.setName(VaccineAppoint.getName());
      updateVaccineAppoint.setAge(VaccineAppoint.getAge());
      updateVaccineAppoint.setAddress(VaccineAppoint.getAddress());
      updateVaccineAppoint.setPhone(VaccineAppoint.getPhone());
      updateVaccineAppoint.setSex(VaccineAppoint.getSex());
      vaccineAppointService.updateById(updateVaccineAppoint);
      return responseModel;
   }

   @GetMapping("/findVaccineAppoint/{id}")
   public ResponseModel<VaccineAppoint> findVaccineAppoint(@PathVariable("id") String id) {
      ResponseModel responseModel = new ResponseModel();

      if(null == id) {
         throw new BusinessException("入参为空");
      }

      VaccineAppoint VaccineAppoint = vaccineAppointService.getById(id);
      responseModel.setSuccess(true);
      responseModel.setData(VaccineAppoint);
      return responseModel;
   }

   @DeleteMapping("/deleteVaccineAppoint/{id}")
   public ResponseModel<String> deleteVaccineAppointById(@PathVariable String id) {
      ResponseModel responseModel = new ResponseModel();

      if(null == id) {
         throw new BusinessException("角色id为空");
      }

      vaccineAppointService.removeById(id);
      responseModel.setSuccess(true);
      return responseModel;
   }

   // 批量删除
   @DeleteMapping("/deleteVaccineAppointByIds")
   public ResponseModel<String> deleteVaccineAppointByIds(@RequestBody String[] ids) {
      ResponseModel responseModel = new ResponseModel();

      if(null == ids) {
         throw new BusinessException("入参为空");
      }

      vaccineAppointService.removeByIds(Arrays.asList(ids));
      responseModel.setSuccess(true);
      return responseModel;
   }
}

