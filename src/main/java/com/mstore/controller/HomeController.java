package com.mstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mstore.domain.Category;
import com.mstore.service.CategoryService;


@Controller
@RequestMapping("/mstore/")
public class HomeController {

	
	@RequestMapping({"home", "/"})
	public String homePage() {
		
		return "site/index";
	}
	
	@RequestMapping({"about"})
	public String homeAbout() {
		
		return "site/about";
	}
	
	@RequestMapping({"contact"})
	public String homeContact() {
		
		return "site/contact";
	}
	
}
