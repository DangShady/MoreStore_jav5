package com.mstore.controller;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mstore.domain.Account;
import com.mstore.domain.Category;
import com.mstore.repository.AccountDAO;
import com.mstore.service.AccountService;
import com.mstore.utils.CookieService;
import com.mstore.utils.MailInfo;
import com.mstore.utils.MailService;

@Controller
@RequestMapping("/mstore/")

public class AccountController {
	
	
	@Autowired
	AccountService accService;
	
	@Autowired
	AccountDAO accDao;
	
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CookieService cookie;
	
	@Autowired
	MailService mailer;
	
	@Autowired
	HttpServletRequest request;
	
	
	@GetMapping("account/login")
	public String viewLogin(Model model) {
		
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
		else if(user.getActivated() == false) {
			model.addAttribute("message","Đăng nhập thất bại: Tài khoản của bạn chưa được kích hoạt");
		}
		else {
			
			session.setAttribute("USERINJD", user);
			
			//Ghi nhớ bằng cookie
			if(user.getAdmin() == false) {
				if(rm == true) {
					cookie.create("USERINCK", user.getUsername(), 30);
					cookie.create("PASSINCK", user.getPassword(), 30);
				}
				else {
					cookie.delete("USERINCK");
					cookie.delete("PASSINCK");
				}
				String backUrl = (String) session.getAttribute("back-url");
				if(backUrl != null) {
					return "redirect:/mstore/home";
				}
				
				return "redirect:/mstore/";
			}
			else {
				
				String backUrl = (String) session.getAttribute("back-url");
				if(backUrl != null) {
					return "redirect:/admin/admin-page";
				}
				
				return "redirect:/admin/admin-page";
			}			
			
			
		}
		
		return "site/accounts/login";
		
	}
	
	
	@RequestMapping("account/logoff")
	public String logOff() {
		session.removeAttribute("USERINJD");
		
		return "redirect:/mstore/home";
	}
	
	// Đăng kí
	@GetMapping("thank")
	public String thank() {
		return "site/accounts/thankyou";
	}
	
	@GetMapping("account/register")
	public String viewRegister(Account account) {
		
		return "site/accounts/register";
	}
	
	@PostMapping("account/register")
	public String registerAccount(@Valid Account account,BindingResult result,Model model) throws MessagingException {
		
		if(result.hasErrors()) {
			return "site/accounts/register";
		}
		
		account.setAdmin(false);
		account.setActivated(false);
		
		this.accDao.save(account);
		
		String to = account.getEmail();
		String subject = "Kích hoạt tài khoản MStore";
		String url = request.getRequestURL().toString().replace("register", "activated/" + account.getUsername());
		String body = "<a href='"+url+"'>Nhấn vào liên kết để kích hoạt tài khoản của bạn</a>";
		
		MailInfo mail = new MailInfo(to, subject, body);
		
		this.mailer.send(mail);
		
		return "redirect:/mstore/thank";
	}
	
	@GetMapping("account/activated/{username}")
	public String activated(Model model, @PathVariable("username") String username) {
		
		Account account = this.accService.getByUsername(username);
		
		account.setActivated(true);
		
		this.accDao.save(account);
		
		return "redirect:/mstore/account/login";
	}
	
	@GetMapping("account/forgot-password")
	public String viewForgotPass() {
		
		return "site/accounts/forgot-password";
	}
	
	@PostMapping("account/forgot-password")
	public String forgotPass(Model model,
			@RequestParam("username") String username,
			@RequestParam("email") String email
			) throws MessagingException {
		Account account = accService.getByUsername(username);
		
		if(account == null) {
			model.addAttribute("message","Thất bại: Tài khoản của bạn chưa không tồn tại");
		}
		else if(!email.equals(account.getEmail())) {
			model.addAttribute("message","Thất bại: Email đăng kí sai");
		}
		else {
			String to = account.getEmail();
			String subject = "Quên mật khẩu tài khoản MStore";
			String body = "Mật khẩu của bạn là: " + account.getPassword() ;
			
			MailInfo mail = new MailInfo(to, subject, body);
			
			this.mailer.send(mail);
			model.addAttribute("message","Kiểm tra email của bạn để lấy lại mật khẩu");
		}
		
		
		return "site/accounts/forgot-password";
	}
	
	
	@GetMapping("account/profile")
	public String profile() {
		
		return "site/accounts/user-profile";
	}
	
	@PostMapping("account/change-password")
	public String changePassword(Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("newpass") String newpass,
			@RequestParam("confirmpass") String confirmpass
			) {
		
		
		Account account = accService.getByUsername(username);
		
		if(!newpass.equals(confirmpass)) {
			model.addAttribute("message","Xác nhận mật khẩu sai");
		}
		else if(!password.equals(account.getPassword())){
			model.addAttribute("message","Mật khẩu hiện tại không đúng");
		}
		else {
			account.setPassword(newpass);
			
			accDao.save(account);
		}
		return "site/accounts/user-profile";
	}
	
}
