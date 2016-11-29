package cydspx.controller;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.globalInfo.SessionKey;

import cydspx.mode.User;


/*
 * 用于处理一些共有的 用户请求
 * 比如根据用户ID获取用户信息
 */

@Controller
public class UserController {
	
	//@Autowired
	//private UserHandler userHandler;
	
	@RequestMapping("/cydspx/getuserinfo")
	@ResponseBody
	public User getUserByName(HttpSession session){
		 User user  = (User) session.getAttribute(SessionKey.USER_INFO.name());
		 return user;
	}
}
