package com.mstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/mstore/")
public class HomeController {
	
	@RequestMapping({"home", "/"})
	public String homePage() {
		
		return "site/index";
	}
	
}
