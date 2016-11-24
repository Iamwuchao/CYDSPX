package cydspx.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.UserDBServer;
import cydspx.globalInfo.ResponseCode;
import cydspx.globalInfo.SessionKey;
import cydspx.globalInfo.UserType;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;
//import groovyjarjarantlr.collections.List;

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
			try
			{
				userDBServer.insertUser(username, password, userType);
				response.setMessage("生成成功！");
			}
			catch(Exception e)
			{
				response.setMessage("数据库错误");
			}
			
			
		}
		return response;
	}
	
	
	public List<User> getSchoolAdminAndExpertList(HttpSession session)
	{
//		ResponseMessage response = new ResponseMessage();
		
		
		List<User> userList = new ArrayList<User>();
		
		for(User user : userDBServer.getAllUserList())
		{
			if(user.getUserType() == UserType.EXPERT.ordinal() ||
				user.getUserType() == UserType.SCHOOLADMIN.ordinal())
			{
				System.out.println(user.getUserId());
				userList.add(user);
			}
		}
		
		
		return userList;
		
	}
	
	public ResponseMessage deleteUser(HttpSession session, int userId)
	{
		ResponseMessage response = new ResponseMessage();	
		try
		{
			userDBServer.deleteUser(userId);
			response.setMessage("");
		}
		catch(Exception e)
		{
			response.setMessage("数据库发生错误");
		}
		return response;
	}
	
	
	public ResponseMessage setUserPassword(HttpSession session, int userId, String newPassword)
	{
		ResponseMessage response = new ResponseMessage();	
		try
		{
			userDBServer.setUserPassword(userId, newPassword);
			response.setMessage("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setMessage("数据库发生错误");
		}
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
}
