package cydspx.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.UserDBServer;
import cydspx.globalInfo.ResponseCode;
import cydspx.globalInfo.SessionKey;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;

@Component
public class LoginHandler {
	
	@Autowired
	private UserDBServer userDBServer;
	
	public ResponseMessage tryLogin(HttpSession session,String userName,String password){
		
		ResponseMessage response = new ResponseMessage();
		
		if(userName==null || password==null){
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("用户名密码不能为空");
		}
		System.out.println("FFF---");
		User user = userDBServer.getUserByName(userName);
		System.out.println("FFF---eeee");
		if(user!=null && user.getPassword().equals(password)){
			System.out.println("###### "+user.getUserName());
			session.setAttribute(SessionKey.USER_INFO.name(), user);
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("登录成功");
		}
		else{
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("用户名或密码错误");
		}
		return response;
	}
}
