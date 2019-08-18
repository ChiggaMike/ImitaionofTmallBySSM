package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Property;

public interface PropertyService {
	
	public void add(Property p);
	
	public void delete(int id);
	
	public void update(Property p);
	
	public Property get(int id);
	
	public List list(int cid);

}
