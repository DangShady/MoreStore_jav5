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
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(global).addPathPatterns("/mstore/**");
		
		registry.addInterceptor(auth).addPathPatterns("/mstore/account/profile","/admin/admin-page",
				"/mstore/account/logoff","/mstore/order-cart/**");
	}
	
}	


