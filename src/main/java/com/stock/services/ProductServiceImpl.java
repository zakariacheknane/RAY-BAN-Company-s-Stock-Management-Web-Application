package com.stock.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stock.entities.Product;
import com.stock.respositories.ProductRepository;
@Transactional(noRollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService{
	private ProductRepository productRepository;
	

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		try {
		// TODO Auto-generated method stub
		return productRepository.findAll();
		}catch (Exception e) {
			throw new RuntimeException("An error occurred while retrieving commandes.", e);
		}
	}

	@Override
	public Product find(Long codeArt) {
		// TODO Auto-generated method stub
		return productRepository.find(codeArt);
	}

	@Override
	public void create(Product product) {
		productRepository.create(product);
		
	}

	@Override
	public void update(Product product) {
		productRepository.update(product);
		
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
		
	}

}
