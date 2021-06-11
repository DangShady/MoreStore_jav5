package com.mstore.admin.controller;

import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Account;
import com.mstore.domain.Order;
import com.mstore.repository.AccountDAO;
import com.mstore.service.CustomerService;
import com.mstore.service.OrderService;
import com.mstore.utils.MailInfo;

@Controller
@RequestMapping("/admin/")
public class CustomerController {
	
	@Autowired()
	CustomerService cusService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountDAO accDao;
	
	@Autowired
	HttpSession session;
	
	
	@GetMapping("customer")
	public String listCustomer(Model model) {
		
		
		return listCustomerByPage(model,1);
	}
	
	
	@GetMapping("customer/page/{pageNumber}")
	public String listCustomerByPage(Model model,@PathVariable("pageNumber") int currentPage) {
		
		
		Page<Account> page = cusService.getAllCustomer(currentPage);
		
		long totalItems= page.getTotalElements();
		
		int totalPages = page.getTotalPages();
		
		
		List<Account> listCustomer = page.getContent();
		
		model.addAttribute("currentPage",currentPage);	
		
		model.addAttribute("totalItems",totalItems);
		model.addAttribute("totalPages",totalPages);
		
		
		model.addAttribute("customers",listCustomer);
		
		return "admin/customers/customer";
	}
	
	
	@RequestMapping("customer-detail/{username}")
	public String customerDetail(Model model,@PathVariable String username) {
		
		
		Account customer = accDao.getById(username);
		
		model.addAttribute("account",customer);
		
		if(customer.getActivated() == true) {
			return "admin/customers/customer-detail";
		}
		else {
			return "redirect:/admin/customer";
		}	
		
	}
	
	@GetMapping("add-admin")
	public String formAddAdmin(Model model,Account account) {
		
		return "admin/customers/add-customer";
	}
	
	@PostMapping("add-admin")
	public String registerAccount(@Valid Account account,BindingResult result,Model model){
		
		if(result.hasErrors()) {
			return "admin/customers/add-customer";
		}
		
		account.setDateregister(new Date());
		account.setAdmin(true);
		account.setActivated(true);
			
		this.accDao.save(account);
		
		return "redirect:/admin/customer";
	}
	
	@GetMapping("change-admin")
	public String changeAdmin(Model model,Account account) {
		
		return "admin/customers/setting-admin";
	}
	
	@PostMapping("change-admin")
	public String changeAccountAdmin(@Valid Account account,BindingResult result,Model model){
		
		if(result.hasErrors()) {
			return "admin/customers/setting-admin";
		}
		
		Account acc = (Account) session.getAttribute("ADMININJD");
		
		account.setUsername(acc.getUsername());
		account.setActivated(acc.getActivated());
		account.setDateregister(acc.getDateregister());
		account.setAdmin(acc.getAdmin());
		
		this.accDao.save(account);
		
		session.setAttribute("ADMININJD", account);
		
		return "redirect:/admin/admin-page";
	}
	
}
