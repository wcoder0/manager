package com.manager.system.model;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> implements Serializable {

   private boolean success;

   private T data;

   private String message;

   private Integer code;

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public void setData(T data) {
      this.data = data;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public void setCode(Integer code) {
      this.code = code;
   }
}
