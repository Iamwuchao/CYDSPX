package cydspx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.handler.LoginHandler;
import cydspx.mode.ResponseMessage;


/*
 * 用户登录
 */
@Controller
public class LoginController {

	@Autowired
	private LoginHandler loginHandler;
	
	@RequestMapping("/cydspx/login")
	@ResponseBody
	public ResponseMessage login(HttpSession session,@RequestParam String userName,@RequestParam String password){
		return loginHandler.tryLogin(session, userName, password);
	}
}
