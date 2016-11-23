package cydspx.handler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import cydspx.globalInfo.ResponseCode;
import cydspx.globalInfo.SessionKey;
import cydspx.mode.ResponseMessage;

@Component
public class LoginHandler {
	
	public ResponseMessage tryLogin(HttpSession session,String userName,String password){
		
		ResponseMessage response = new ResponseMessage();
		
		if(userName==null || password==null){
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("用户名密码不能为空");
		}
		session.setAttribute(SessionKey.USER_NAME.name(), userName);
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("登录成功");
		return response;
	}
}
