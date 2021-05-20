package com.manager.system.controller;

import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

   private Logger logger = LoggerFactory.getLogger(IndexController.class);

   @RequestMapping("/index")
   public String index() {
      return "index";
   }

   @RequestMapping("/toLogin")
   public String toLogin() {
      return "toLogin";
   }

   @RequestMapping("/userManager")
   public String userManager() {
      return "userManager/userManager";
   }


   @RequestMapping("/userForm")
   public String userForm() {
      return "userManager/userForm";
   }


   @RequestMapping("/apponitManager")
   public String appointManager(HttpServletRequest request) {
      return "apponitManager/apponitManager";
   }

   @RequestMapping("/reportManager")
   public String reportManager(HttpServletRequest request) {
      return "reportManager/reportManager";
   }

   @RequestMapping("/ownerManager")
   public String ownerManager(HttpServletRequest request) {
      return "ownerManager/ownerManager";
   }


   @RequestMapping("/appointForm")
   public String appointForm() {
      return "appointManager/appointForm";
   }

   @RequestMapping("/reportForm")
   public String reportForm() {
      return "reportManager/reportForm";
   }

   @GetMapping("/logout")
   public String logout(HttpServletRequest request) {
      request.getSession().removeAttribute("loginUser");
      return "toLogin";
   }

}
