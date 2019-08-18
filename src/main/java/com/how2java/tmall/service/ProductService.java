package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;

public interface ProductService {
	
	public void add(Product p);
	
	public void delete(int id);
	
	public void update(Product p);
	
	public Product get(int id);
	
	public List list(int cid);
	
	public void setFirstProductImage(Product p);
	
	public void fill(List<Category> cs);
	
	public void fill(Category c);
	
	public void fillByRow(List<Category> cs);
	
	public void setSaleAndReviewNumber(Product p);
	
	public void setSaleAndReviewNumber(List<Product> ps);
	
	public List<Product> search(String keyword);
}
