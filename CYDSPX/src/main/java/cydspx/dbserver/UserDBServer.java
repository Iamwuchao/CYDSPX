package cydspx.dbserver;

import lombok.Data;

import java.util.List;

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
		try{
		return userMapper.getUserByUserName(userName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void insertUser(String username, String  password,int  type)
	{
		userMapper.insertUser(username, password, type);		
	}
	
	public List<User> getAllUserList()
	{
		return userMapper.getAllUserList();
	}
	
	
	public void deleteUser(int userId)
	{
		userMapper.deleteUser(userId);		
	}
	
	
	public void setUserPassword(int userId, String password)
	{
		userMapper.setUserPassword(userId, password);		
	}
	
	public List<User> getAllUsersByType(int type){
		return userMapper.getAllUsersByType(type);
	}
}
