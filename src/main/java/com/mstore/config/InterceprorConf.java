package com.mstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Component	
public class InterceprorConf implements WebMvcConfigurer{
	
	@Autowired
	GlobalModelConf global;
	
	@Autowired
	AuthorizeInterceptor auth;
	
	@Autowired
	AuthorizeAdminInterceptor authAdmin;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(global).addPathPatterns("/mstore/**").excludePathPatterns("/static/**");
		
		registry.addInterceptor(auth).addPathPatterns("/mstore/account/profile",
				"/mstore/account/logoff","/mstore/order-cart/**","/mstore/history-detail/**");
		
		registry.addInterceptor(authAdmin).addPathPatterns("/admin/**");
	}
	
}	


