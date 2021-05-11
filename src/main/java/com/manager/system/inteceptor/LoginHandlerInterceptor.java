package com.manager.system.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      Object user = request.getSession().getAttribute("loginUser");

      if(user == null) {
         request.setAttribute("msg", "无权限请先登录");
         request.getRequestDispatcher("/toLogin").forward(request, response);
         return false;
      }
      else {
         return true;
      }
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                          ModelAndView modelAndView) throws Exception {

   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                               Exception ex) throws Exception {

   }
}
