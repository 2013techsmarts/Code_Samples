package org.smarttechie.graphqldemo.graphql;

import java.util.Optional;

import org.smarttechie.graphqldemo.model.Product;
import org.smarttechie.graphqldemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
	
	@Autowired
	private ProductService productService ;
	
	public Optional<Product> getProduct(String id) {
		return productService.getProduct(id);
	}
	
	
}
