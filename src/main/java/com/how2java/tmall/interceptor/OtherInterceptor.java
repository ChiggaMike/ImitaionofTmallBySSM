package com.how2java.tmall.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.OrderItemService;

public class OtherInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	OrderItemService orderItemService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception{
		//获取分类信息，用于放在搜索栏下面
		List<Category> cs = categoryService.list();
		request.getSession().setAttribute("cs", cs);
		//获取当前的contextPath:tmall_ssm，用于放在左上角的变形金刚图片
		//点击之后才能跳转到首页
		HttpSession session = request.getSession();
		String contextPath = session.getServletContext().getContextPath();
		request.getSession().setAttribute("contextPath", contextPath + "/forehome");
		//获取购物车中一共有多少数量
		User user = (User) session.getAttribute("user");
		int cartTotalItemNumber = 0;
		if (user != null){
			List<OrderItem> ois = orderItemService.listByUser(user.getId());
			for (OrderItem oi:ois){
				cartTotalItemNumber += oi.getNumber();
			}
		}
		request.getSession().setAttribute("cartTotalItemNumber", cartTotalItemNumber);
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
			Exception ex){
		
	}
}
