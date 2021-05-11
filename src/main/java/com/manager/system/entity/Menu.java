package com.manager.system.entity;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

   private Integer menuId;
   private String menuName;
   private Integer type;
   private String href;
   private Integer target;
   private Integer parentId;

   public Integer getMenuId() {
      return menuId;
   }

   public void setMenuId(Integer menuId) {
      this.menuId = menuId;
   }

   public String getMenuName() {
      return menuName;
   }

   public void setMenuName(String menuName) {
      this.menuName = menuName;
   }

   public Integer getType() {
      return type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public String getHref() {
      return href;
   }

   public void setHref(String href) {
      this.href = href;
   }

   public Integer getTarget() {
      return target;
   }

   public void setTarget(Integer target) {
      this.target = target;
   }

   public Integer getParentId() {
      return parentId;
   }

   public void setParentId(Integer parentId) {
      this.parentId = parentId;
   }
}
