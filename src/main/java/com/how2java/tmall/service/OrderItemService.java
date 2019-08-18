package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;

public interface OrderItemService {
	
	public void add(OrderItem oi);
	
	public void delete(int id);
	
	public void update(OrderItem oi);
	
	public OrderItem get(int id);
	
	public List list();
	
	public void fill(List<Order> os);
	
	public void fill(Order o);

	public int getSaleCount(int pid);
	
	public List<OrderItem> listByUser(int uid);
}
