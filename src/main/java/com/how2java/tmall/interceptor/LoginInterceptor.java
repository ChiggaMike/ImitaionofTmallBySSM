package com.how2java.tmall.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.OrderItemService;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	
	@Autowired
	OrderItemService orderItemService;
	
	//处理请求前调用，如果返回false, 从当前拦截器往回执行所有拦截器的afterCompletion()，然后退出拦截链
	//如果返回true, 执行下一个拦截器，直到所有拦截器执行完毕后，再执行被拦截的Controller
	//然后从最后一个拦截器往回执行所有的postHandle()，然后再从最后一个拦截器往回执行所有的afterCompletion()，然后退出拦截链
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession();
		String contextPath = session.getServletContext().getContextPath();
		String[] noNeedAuthPage = new String[]{
				"home",
				"checklogin",
				"register",
				"loginAjax",
				"login",
				"product",
				"category",
				"search"
		};
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		if (uri.startsWith("/fore")){
			String method = StringUtils.substringAfterLast(uri, "/fore");
			if (!Arrays.asList(noNeedAuthPage).contains(method)){
				User user = (User) session.getAttribute("user");
				if (user == null){
					response.sendRedirect("loginPage");
					return false;
				}
			}
		}
		return true;
	}
	
	//在业务处理器请求执行完成后、生成试图之前执行的方法，可在modelAndView中加入数据
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception{
		
	}
	
	//在DispatchServlet完全处理完请求后调用，可用于清理资源等
	//当有拦截器抛出异常时， 会从当前拦截器往回执行所有的拦截器的afterCompletion()
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception{
		
	}
	
}
