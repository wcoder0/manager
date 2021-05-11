package com.manager.system.controller;


import java.net.URISyntaxException;
import java.util.*;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manager.system.entity.EpidemicReport;
import com.manager.system.exception.BusinessException;
import com.manager.system.model.ResponseModel;
import com.manager.system.service.EpidemicReportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hannibal
 * @since 2021-05-09
 */
@RestController
@RequestMapping("/epidemicReport")
public class EpidemicReportController {


   @Autowired
   private EpidemicReportService epidemicReportService;

   @GetMapping("/findEpidemicReports")
   public ResponseModel<Page<EpidemicReport>> finEpidemicReports(EpidemicReport EpidemicReport,
                                                                 Page<EpidemicReport> page) {
      QueryWrapper<EpidemicReport> query = new QueryWrapper<>();
      ResponseModel responseModel = new ResponseModel();

      if(EpidemicReport != null && StringUtils.isNotBlank(EpidemicReport.getName())) {
         query.like("name", EpidemicReport.getName());
      }

      if(EpidemicReport != null && StringUtils.isNotBlank(EpidemicReport.getAddress())) {
         query.like("address", EpidemicReport.getAddress());
      }

      try {
         page = epidemicReportService.page(page, query);
         responseModel.setSuccess(true);
         responseModel.setCode(0);
         responseModel.setData(page);
      }
      catch(Exception e) {
         responseModel.setSuccess(false);
         responseModel.setCode(-1);
         responseModel.setMessage("查询填报失败");
         e.printStackTrace();
      }


      return responseModel;
   }


   @PostMapping("/addEpidemicReport")
   public ResponseModel<EpidemicReport> addEpidemicReport(@RequestBody EpidemicReport epidemicReport) throws URISyntaxException {
      ResponseModel responseModel = new ResponseModel();

      try {
         epidemicReport.setReportTime(new Date());
         epidemicReportService.save(epidemicReport);
         responseModel.setSuccess(true);
         responseModel.setData(epidemicReport);
      }
      catch(Exception e) {
         responseModel.setSuccess(false);
         responseModel.setMessage("添加填报失败");
         e.printStackTrace();
      }

      return responseModel;
   }

   @PutMapping("/updateEpidemicReport")
   public ResponseModel<EpidemicReport> updateEpidemicReport(@RequestBody EpidemicReport epidemicReport) {
      ResponseModel responseModel = new ResponseModel();

      if(epidemicReport.getId() == null) {
         throw new BusinessException("填报id不能为空");
      }

      EpidemicReport updateEpidemicReport = epidemicReportService.getById(epidemicReport.getId());

      if(updateEpidemicReport == null) {
         throw new BusinessException("更新填报失败,填报不存在");
      }

      updateEpidemicReport.setName(epidemicReport.getName());
      updateEpidemicReport.setAge(epidemicReport.getAge());
      updateEpidemicReport.setAddress(epidemicReport.getAddress());
      updateEpidemicReport.setPhone(epidemicReport.getPhone());
      updateEpidemicReport.setSex(epidemicReport.getSex());
      epidemicReportService.updateById(updateEpidemicReport);
      return responseModel;
   }

   @GetMapping("/findEpidemicReport/{id}")
   public ResponseModel<EpidemicReport> findEpidemicReport(@PathVariable("id") String id) {
      ResponseModel responseModel = new ResponseModel();

      if(null == id) {
         throw new BusinessException("入参为空");
      }

      EpidemicReport epidemicReport = epidemicReportService.getById(id);
      responseModel.setSuccess(true);
      responseModel.setData(epidemicReport);
      return responseModel;
   }

   @DeleteMapping("/deleteEpidemicReport/{id}")
   public ResponseModel<String> deleteEpidemicReportById(@PathVariable String id) {
      ResponseModel responseModel = new ResponseModel();

      if(null == id) {
         throw new BusinessException("填报id为空");
      }

      epidemicReportService.removeById(id);
      responseModel.setSuccess(true);
      return responseModel;
   }

   // 批量删除
   @DeleteMapping("/deleteEpidemicReportByIds")
   public ResponseModel<String> deleteEpidemicReportByIds(@RequestBody String[] ids) {
      ResponseModel responseModel = new ResponseModel();

      if(null == ids) {
         throw new BusinessException("入参为空");
      }

      epidemicReportService.removeByIds(Arrays.asList(ids));
      responseModel.setSuccess(true);
      return responseModel;
   }

}

