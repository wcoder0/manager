package com.manager.system.security;

import com.manager.system.inteceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      //注册TestInterceptor拦截器
      InterceptorRegistration registration = registry.addInterceptor(new LoginHandlerInterceptor());
      registration.addPathPatterns("/**");                      //所有路径都被拦截
      registration.excludePathPatterns("/css/**")
            .excludePathPatterns("/lib/**")
            .excludePathPatterns("/login/**")
            .excludePathPatterns("/logout")
            .excludePathPatterns("/owner/getImage/**")
            .excludePathPatterns("/epidemicReport/addEpidemicReport")
            .excludePathPatterns("/vaccineAppoint/addVaccineAppoint")
            .excludePathPatterns("/owner/addOwner")
      .excludePathPatterns("/toLogin");
   }
}
