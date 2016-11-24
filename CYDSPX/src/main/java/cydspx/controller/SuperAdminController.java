package cydspx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.handler.LoginHandler;
import cydspx.handler.SuperAdminHandler;
import cydspx.mode.ResponseMessage;


//public class LoginController {
//
//	
//}
@Controller
public class SuperAdminController {
	@Autowired
	private SuperAdminHandler superAdminHandler;

	@RequestMapping("/cydspx/superAdmin/addUser")
	@ResponseBody
	public ResponseMessage addUserPage(HttpSession session,
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String type
			) 
	{
		return superAdminHandler.addUser(session, username, password, type);
	}
}











