package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.ReviewMapper;
import com.how2java.tmall.pojo.Review;
import com.how2java.tmall.pojo.ReviewExample;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.ReviewService;
import com.how2java.tmall.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	UserService userService;
	
	public void add(Review r){
		reviewMapper.insert(r);
	}
	
	public void delete(int id){
		reviewMapper.deleteByPrimaryKey(id);
	}
	
	public void update(Review r){
		reviewMapper.updateByPrimaryKeySelective(r);
	}
	
	public Review get(int id){
		return reviewMapper.selectByPrimaryKey(id);
	}
	
	public List<Review> list(int pid){
		ReviewExample example = new ReviewExample();
		example.createCriteria().andPidEqualTo(pid);
		example.setOrderByClause("id desc");
		List<Review> reviews = reviewMapper.selectByExample(example);
		setUser(reviews);
		return reviews;
	}
	
	public int getCount(int pid){
		return list(pid).size();
	}
	
	public void setUser(List<Review> reviews){
		for (Review r:reviews)
			setUser(r);
	}
	
	public void setUser(Review r){
		User u = userService.get(r.getUid());
		r.setUser(u);
	}
}
