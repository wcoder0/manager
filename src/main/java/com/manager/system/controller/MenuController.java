package com.manager.system.controller;

import java.util.*;

import com.manager.system.entity.Menu;
import com.manager.system.model.ResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuController {

   @GetMapping("/findMenus")
   public List<Menu> findMenus() {
      ResponseModel responseModel = new ResponseModel();

      List<Menu> menus = new ArrayList<>();
      Menu menu = new Menu();
      menu.setMenuId(1);
      menu.setMenuName("用户管理");
      menu.setHref("userManager");
      menu.setTarget(0);
      menu.setType(0);
      menus.add(menu);

      Menu menu1 = new Menu();
      menu1.setMenuId(2);
      menu1.setMenuName("疫情填报");
      menu1.setHref("reportManager");
      menu1.setTarget(0);
      menu1.setType(0);
      menus.add(menu1);

      Menu menu2 = new Menu();
      menu2.setMenuId(3);
      menu2.setMenuName("疫苗预约");
      menu2.setHref("apponitManager");
      menu2.setTarget(0);
      menu2.setType(0);
      menus.add(menu2);


      Menu menu3 = new Menu();
      menu3.setMenuId(4);
      menu3.setMenuName("业主信息");
      menu3.setHref("ownerManager");
      menu3.setTarget(0);
      menu3.setType(0);
      menus.add(menu3);

      responseModel.setSuccess(true);
      responseModel.setData(menus);
      return menus;
      //return responseModel;
   }

}
