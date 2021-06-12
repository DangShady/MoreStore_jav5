package com.mstore.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mstore.domain.Account;
import com.mstore.domain.Category;
import com.mstore.service.CategoryService;

@SuppressWarnings("deprecation")
@Component
public class AuthorizeAdminInterceptor implements HandlerInterceptor {

		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		Account accountAdmin = (Account) session.getAttribute("ADMININJD");
		
		if(accountAdmin == null) {
			session.setAttribute("back-url-admin", request.getRequestURI());
			response.sendRedirect("/mstore/account/login");
			
			return false;
		}
		
		return true;
	}
	
}
