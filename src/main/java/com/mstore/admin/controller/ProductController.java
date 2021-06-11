package com.mstore.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mstore.domain.Account;
import com.mstore.domain.Category;
import com.mstore.domain.Product;
import com.mstore.helps.ConvertString;
import com.mstore.helps.UploadsFile;
import com.mstore.repository.CategoryDAO;
import com.mstore.repository.ProductDAO;
import com.mstore.service.ProductService;

@Controller
@RequestMapping("/admin/")
public class ProductController {

	@Autowired
	ServletContext app;

	@Autowired(required = false)
	ConvertString convert;

	@Autowired
	ProductDAO proDao;

	@Autowired
	private CategoryDAO cateDao;

	@Autowired
	private ProductService proService;

	@GetMapping("product")
	public String products(Model model) {

		return listProductByPage(model, 1);
	}

	@GetMapping("product/page/{pageNumber}")
	public String listProductByPage(Model model, @PathVariable("pageNumber") int currentPage) {

		Page<Product> page = this.proService.listAll(currentPage);

		long totalItems = page.getTotalElements();

		int totalPages = page.getTotalPages();

		List<Product> listProduct = page.getContent();

		model.addAttribute("currentPage", currentPage);

		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("products", listProduct);

		return "admin/product/product";
	}

	@ModelAttribute("categorys")
	public List<Category> getCategory() {
		return cateDao.findAll();
	}

	@GetMapping("form-product")
	public String formAddProduct(Product product, Model model) {

		return "admin/product/add-product";
	}

	@PostMapping("add-product")
	public String addCategory(@Valid Product product, BindingResult result,
			@RequestParam("file_product") MultipartFile file, Model model) throws IllegalStateException, IOException {

		if (result.hasErrors()) {
			return "admin/product/update-product";
		}

		if (file.isEmpty()) {
			product.setImage("no-product.png");

		} else {

			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			product.setImage(fileName);
			

			String uploadDir = "./src/main//resources/static/images/products/" + product.getName();

			UploadsFile.uploadImage(file, uploadDir, fileName);
		}

		this.proDao.save(product);

		return "redirect:product";
	}

	@GetMapping("edit-product/{id}")
	public String editProduct(@PathVariable("id") int id, Model model) {
		Product product = proDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		;
		model.addAttribute("product", product);
		
		System.out.println("Image" + product.getImage() + product.getId() + product.getCategory().getId());
		
		
		return "admin/product/update-product";
	}

	@PostMapping("update-product-admin/{idproduct}")
	public String updateProduct(@PathVariable("idproduct") int id, @Valid Product product, BindingResult result,
			@RequestParam("file_update") MultipartFile file, Model model) throws IllegalStateException, IOException {
		
		if (result.hasErrors()) {
			return "admin/product/update-product";
		}
		
		if (!file.isEmpty()) {

			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
			product.setImage(fileName);

			String uploadDir = "./src/main//resources/static/images/products/" + product.getName();

		
			UploadsFile.uploadImage(file, uploadDir, fileName);
		}

		proDao.save(product);
		return "redirect:/admin/product";
	}

	@GetMapping("delete-product/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
		Product product = proDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		this.proDao.delete(product);


		return "redirect:/admin/product";
	}
}
