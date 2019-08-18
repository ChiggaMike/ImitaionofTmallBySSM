package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;

public interface OrderService {
	
	String waitPay = "waitPay";
	String waitDelivery = "waitDelivery";
	String waitConfirm = "waitConfirm";
	String waitReview = "waitReview";
	String finish = "finish";
	String delete = "delete";
	
	public void add(Order o);
	
	public void delete(int id);
	
	public void update(Order o);
	
	public Order get(int id);
	
	public List list();
	
	public float add(Order o, List<OrderItem> ois);
	
	public List list(int uid, String excludedStatus);

}
