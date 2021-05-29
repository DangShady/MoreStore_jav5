package com.mstore.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mstore.domain.Category;
import com.mstore.domain.Product;
import com.mstore.helps.ConvertString;
import com.mstore.repository.CategoryDAO;
import com.mstore.repository.ProductDAO;

@Controller
@RequestMapping("/admin/")
public class ProductController {

	@Autowired
	ServletContext app;
	
	@Autowired(required = false)
	ConvertString convert;
	
	@Autowired
	private ProductDAO proDao;
	
	@Autowired
	private CategoryDAO cateDao;
	
	
	@GetMapping("product")
	public String products(Model model) {
		
		
		model.addAttribute("products", this.proDao.findAll());
		
		
		return "admin/product/product";
		
	}
	
	@ModelAttribute("categorys")
	public List<Category> getCategory(){
		return cateDao.findAll();
	}
	
	@GetMapping("form-product")
	public String formAddProduct(Product product,Model model) {
		
		
		
		return "admin/product/add-product";
	}
	
	@PostMapping("add-product")
	public String addCategory(@Valid Product product,BindingResult result,@RequestParam("file_product") MultipartFile file,
			Model model) throws IllegalStateException, IOException{
		
		if(result.hasErrors()) {
			return "admin/product/add-category";
		}
		
//		String path = app.getRealPath("/static/images/products/"+product.getName());
//		File fileDir = new File(path);
//		if(!fileDir.exists()) {
//			fileDir.mkdirs();
//		}
//		System.out.println("File link: " + fileDir);
//		if(file.isEmpty()) {
//			product.setImage("no_product.png");
//		}
//		else {
//			product.setImage(file.getOriginalFilename());
//			
//			file.transferTo(new File(path+"/"+product.getImage()));
//			
//			System.out.println("File link: " + path+"/"+product.getImage());
//			
//		}
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		product.setImage(fileName);
		
		this.proDao.save(product);
		
		String uploadDir = "./src/main//resources/static/images/products/" + product.getName();
		
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try(InputStream inputStream = file.getInputStream()){
		
		Path filePath = uploadPath.resolve(fileName);
		
		System.out.println("File name: "+ filePath.toFile().getAbsolutePath());
		
		Files.copy(inputStream, filePath ,StandardCopyOption.REPLACE_EXISTING);
		
		}catch (Exception e) {
			throw new IOException("Could not save upload file:" + fileName);
		}
		
		return "redirect:product";
	}
}
