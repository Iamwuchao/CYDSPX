package cydspx.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.UserDBServer;
import cydspx.globalInfo.ResponseCode;
import cydspx.globalInfo.SessionKey;
import cydspx.globalInfo.UserType;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;

@Component
public class SuperAdminHandler {
	
	@Autowired
	private UserDBServer userDBServer;
	
	public ResponseMessage addUser(HttpSession session, String username,String password, String type){
		System.out.println(username);
		System.out.println(password);
		System.out.println(type);
		ResponseMessage response = new ResponseMessage();
		int userType = -1;
		if(type == null){}
		else if(type.equals("schoolAdmin"))
		{
			userType = UserType.SCHOOLADMIN.ordinal();
		}
		else if(type.equals("expert"))
		{
			
			userType = UserType.SCHOOLADMIN.ordinal();
		}
		else {}
		
		if(username == null || username.isEmpty())
		{
			response.setMessage("用户名不能为空");
		}
		else if(password == null || password.isEmpty())
		{
			response.setMessage("密码不能为空");
		}
		else if(userType == -1)
		{
			response.setMessage("账户类型不正确");
		}
		else
		{
			userDBServer.insertUser(username, password, userType);
			response.setMessage("生成成功！");
		}
		return response;
	}
}
