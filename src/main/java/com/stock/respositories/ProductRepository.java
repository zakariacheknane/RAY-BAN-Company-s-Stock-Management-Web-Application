package com.stock.respositories;

import java.util.List;

import com.stock.entities.Product;



public interface ProductRepository {
	public List<Product> findAll();
	public Product find(Long codeArt);
	public void create(Product product);
	public void update(Product product);
	public void delete(Product product);
	
	

}
