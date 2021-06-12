package com.mstore.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mstore.domain.Category;
import com.mstore.service.CategoryService;

@SuppressWarnings("deprecation")
@Component
public class GlobalModelConf implements HandlerInterceptor {

		
	@Autowired
	CategoryService cateService;
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		List<Category> getCategoryShirt = cateService.getCategoryToShirt();

		List<Category> getCategoryPant = cateService.getCategoryToPant();

		List<Category> getCategoryAccessories = cateService.getCategoryToAccessories();

		request.setAttribute("shirts", getCategoryShirt);

		request.setAttribute("pants", getCategoryPant);

		request.setAttribute("accessories", getCategoryAccessories);
		

	}
	
	
}
