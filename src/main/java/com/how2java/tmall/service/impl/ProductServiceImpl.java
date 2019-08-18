package com.how2java.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.ProductMapper;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductExample;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.ReviewService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductMapper productMapper;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ReviewService reviewService;
	
	public void add(Product p){
		productMapper.insert(p);
	}
	
	public void delete(int id){
		productMapper.deleteByPrimaryKey(id);
	}
	
	public void update(Product p){
		productMapper.updateByPrimaryKeySelective(p);
	}
	
	public Product get(int id){
		Product p = productMapper.selectByPrimaryKey(id);
		setCategory(p);
		setFirstProductImage(p);
		return p;
	}
	
	public void setCategory(List<Product> ps){
		for (Product p:ps)
			setCategory(p);
	}
	
	public void setCategory(Product p){
		int cid = p.getCid();
		Category c = categoryService.get(cid);
		p.setCategory(c);
	}
	
	public List list(int cid){
		ProductExample example = new ProductExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");
		List result = productMapper.selectByExample(example);
		setCategory(result);
		setFirstProductImage(result);
		return result;
	}
	
	public void setFirstProductImage(List<Product> ps){
		for (Product p:ps)
			setFirstProductImage(p);
	}
	
	public void setFirstProductImage(Product p){
		List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);
		if (!pis.isEmpty()){
			ProductImage pi = pis.get(0);
			p.setFirstProductImage(pi);
		}
	}
	
	public void fill(List<Category> cs){
		for (Category c:cs)
			fill(c);
	}
	
	public void fill(Category c){
		List<Product> ps = list(c.getId());
		c.setProducts(ps);
	}
	
	public void fillByRow(List<Category> cs){
		int productNumberEachRow = 8;
		for (Category c:cs){
			List<Product> products = c.getProducts();
			List<List<Product>> productsByRow = new ArrayList<>();
			for (int i = 0; i < products.size(); i += productNumberEachRow){
				int size = i + productNumberEachRow;
				size = size > products.size() ? products.size() : size;
				List<Product> productsOfEachRow = products.subList(i, size);
				productsByRow.add(productsOfEachRow);
			}
			c.setProductsByRow(productsByRow);
		}
	}
	
	public void setSaleAndReviewNumber(List<Product> ps){
		for (Product p:ps)
			setSaleAndReviewNumber(p);
	}
	
	public void setSaleAndReviewNumber(Product p){
		int saleCount = orderItemService.getSaleCount(p.getId());
		p.setSaleCount(saleCount);
		int reviewCount = reviewService.getCount(p.getId());
		p.setReviewCount(reviewCount);
	}
	
	public List<Product> search(String keyword){
		ProductExample example = new ProductExample();
		example.createCriteria().andNameLike("%" + keyword + "%");
		example.setOrderByClause("id desc");
		List result = productMapper.selectByExample(example);
		setFirstProductImage(result);
		setCategory(result);
		return result;
	}
}
