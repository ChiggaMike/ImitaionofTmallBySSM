package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;

public interface PropertyValueService {
	
	public void init(Product p);
	
	public void update(PropertyValue pv);
	
	public PropertyValue get(int ptid, int pid);
	
	public List<PropertyValue> list(int pid);
	
}
