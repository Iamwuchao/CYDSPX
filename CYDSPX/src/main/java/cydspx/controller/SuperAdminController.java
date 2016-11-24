package cydspx.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.handler.LoginHandler;
import cydspx.handler.SuperAdminHandler;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;


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
	
	
	@RequestMapping("/cydspx/superAdmin/getSchoolAdminAndExpertList")
	@ResponseBody
	public List<User> getSchoolAdminAndExpertList(HttpSession session) 
	{
//		System.out.println("0000++++++++==");
		return superAdminHandler.getSchoolAdminAndExpertList(session);
	}

	@RequestMapping("/cydspx/superAdmin/deleteUser")
	@ResponseBody
	public ResponseMessage deleteUser(HttpSession session, @RequestParam int userId) 
	{
//		System.out.println("0000++++++++==");
		return superAdminHandler.deleteUser(session, userId);
	}

	
}











