package com.mstore.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mstore.domain.Account;
import com.mstore.domain.Category;
import com.mstore.service.AccountService;
import com.mstore.utils.CookieService;

@Controller
@RequestMapping("/mstore/")

public class AccountController {
	
	
	@Autowired
	AccountService accService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CookieService cookie;
	
	
	@GetMapping("account/login")
	public String login(Model model) {
		
		Cookie ckusername = cookie.read("USERINCK");
		Cookie ckpassword = cookie.read("PASSINCK");
		
		if(ckusername != null) {
			String username = ckusername.getValue();
			String password = ckpassword.getValue();
			
			model.addAttribute("username",username);
			model.addAttribute("password",password);
		}
		
		return "site/accounts/login";
	}
	

	@PostMapping("account/login")
	public String loginToWeb(Model model, @RequestParam("username") String username,
							@RequestParam("password") String password,
							@RequestParam(value="rm",defaultValue = "false") boolean rm
			){
		
		Account user = accService.getByUsername(username);
		
		if(user == null) {	
			model.addAttribute("message","Đăng nhập thất bại: Tài khoản hoặc mật khẩu bị sai");
		}
		else if(!password.equals(user.getPassword())) {
			model.addAttribute("message","Đăng nhập thất bại: Tài khoản hoặc mật khẩu bị sai");
		}
		else {
			
			session.setAttribute("USERINJD", user);
			
			//Ghi nhớ bằng cookie
			if(rm == true) {
				cookie.create("USERINCK", user.getUsername(), 30);
				cookie.create("PASSINCK", user.getPassword(), 30);
			}
			else {
				cookie.delete("USERINCK");
				cookie.delete("PASSINCK");
			}
			
			return "redirect:/mstore/home";
		}
		
		
		return "site/accounts/login";
		
	}
	
}
