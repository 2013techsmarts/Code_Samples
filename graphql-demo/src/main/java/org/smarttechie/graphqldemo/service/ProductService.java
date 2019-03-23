package org.smarttechie.graphqldemo.service;

import java.util.Optional;

import org.smarttechie.graphqldemo.model.Product;
import org.smarttechie.graphqldemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Optional<Product> getProduct(String id) {
		return this.productRepository.findById(id);
	}
}
