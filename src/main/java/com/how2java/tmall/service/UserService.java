package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.User;

public interface UserService {
	
	public void add(User u);
	
	public void delete(int id);
	
	public void update(User u);
	
	public User get(int id);
	
	public List list();
	
	public boolean isExist(String name);
	
	public User get(String name, String password);
}
