package com.how2java.tmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.UserService;
import com.how2java.tmall.util.Page;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("admin_user_list")
	public String list(Model model, Page page){
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<User> us = userService.list();
		
		int total = (int) new PageInfo<>(us).getTotal();
		page.setTotal(total);
		
		model.addAttribute("us", us);
		model.addAttribute("page", page);
		return "admin/listUser";
	}
	
	@RequestMapping("adminTologin")
	public String adminLogin(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session){
		name = HtmlUtils.htmlEscape(name);
		User user = userService.get(name, password);
		if (user == null || !user.getName().equals("Mickey")){
			model.addAttribute("msg", "管理员账号或密码错误");
			return "admin/adminLogin";
		}
		session.setAttribute("user", user);
		return "redirect:admin_category_list";
	}
}
