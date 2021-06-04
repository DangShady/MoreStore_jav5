//package com.mstore.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//
//
//
//@Component	
//public class InterceprorConf extends WebMvcConfigurationSupport{
//	
//	@Autowired
//	GlobalModelConf global;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(global).addPathPatterns("/mstore/**");
//		
//		registry.addInterceptor(global).addPathPatterns("/images/**");
//		
//		registry.addInterceptor(global).addPathPatterns("/site/*");
//	}
//	
//}	
//
//
