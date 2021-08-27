package com.ptit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptit.model.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	Product product1 = new Product(1,"You can win","book.jpg","Đây là cuốn sách dành cho những người muốn thành công", 120000); 
	Product product2 = new Product(2,"Đắc nhân tâm","book.jpg","Đây là cuốn sách dành cho những người muốn thành công", 130000); 
	Product product3 = new Product(3,"Làm giàu không khó","book.jpg","Đây là cuốn sách dành cho những người muốn thành công", 140000); 
	Product product4 = new Product(4,"Cha nghèo cha giàu","book2.jpg","Đây là cuốn sách dành cho những người muốn thành công", 200000); 
	Product product5 = new Product(5,"Dạy con làm giàu","book3.jfif","Đây là cuốn sách dành cho những người muốn thành công", 300000);
	Product product6 = new Product(6,"Tư duy triệu phú","book3.jfif","Đây là cuốn sách dành cho những người muốn thành công", 40000);
	
		@GetMapping("/{id}")
		public String productDetail(@PathVariable long id, ModelMap map) {
			List<Product> arraylist = new ArrayList<Product>(); 
			arraylist.add(product1);
			arraylist.add(product2); 
			arraylist.add(product3); 
			arraylist.add(product4); 
			arraylist.add(product5); 
			arraylist.add(product6); 
			
			boolean checkExist = false; 
			for(Product product: arraylist) {
				if(product.getId()==id) {
					map.addAttribute("product", product);
					checkExist = true; 
					break; 
				}
			}
			if(checkExist==false) {
				return "notfound"; 
			}
			
			return "product-detail"; 
		}
}



















