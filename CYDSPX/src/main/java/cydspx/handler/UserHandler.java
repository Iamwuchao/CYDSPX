package cydspx.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.UserDBServer;
import cydspx.mode.User;

/*
 * 该类用于处理一些共用的用户请求
 */
@Component
public class UserHandler {
	
	@Autowired
	private UserDBServer userDBServer;
	
	public User getUserByName(String userName){
		return userDBServer.getUserByName(userName);
	}
}
