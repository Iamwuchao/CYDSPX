package cydspx.dbserver;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.UserMapper;
import cydspx.mode.User;


@Data
@Service
public class UserDBServer {
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByName(String userName){
		return userMapper.getUserByUserName(userName);
	}
	
	
	public void insertUser(String username, String  password,int  type)
	{
		userMapper.insertUser(username, password, type);
		
	}
	
}
