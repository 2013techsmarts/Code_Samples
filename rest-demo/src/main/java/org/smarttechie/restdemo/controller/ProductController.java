package org.smarttechie.restdemo.controller;

import java.util.Optional;

import org.smarttechie.restdemo.model.Product;
import org.smarttechie.restdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/{id}")
	@ResponseBody
    public Optional<Product> getProduct(@PathVariable("id") String id) {
        return productService.getProduct(id);
    }
}
