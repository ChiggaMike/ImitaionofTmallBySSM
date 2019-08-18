package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Review;

public interface ReviewService {
	
	public void add(Review r);
	
	public void delete(int id);
	
	public void update(Review r);
	
	public Review get(int id);
	
	public List list(int pid);
	
	public int getCount(int pid);

}
