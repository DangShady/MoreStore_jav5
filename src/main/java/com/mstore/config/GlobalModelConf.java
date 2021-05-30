package com.mstore.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mstore.domain.Category;
import com.mstore.service.CategoryService;

@Component
public class GlobalModelConf implements HandlerInterceptor {

	@Autowired
	CategoryService cateService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		List<Category> getCategoryShirt = cateService.getCategoryToShirt();

		List<Category> getCategoryPant = cateService.getCategoryToPant();

		List<Category> getCategoryAccessories = cateService.getCategoryToAccessories();

		modelAndView.addObject("shirts", getCategoryShirt);

		modelAndView.addObject("pants", getCategoryPant);

		modelAndView.addObject("accessories", getCategoryAccessories);
		

	}
}
