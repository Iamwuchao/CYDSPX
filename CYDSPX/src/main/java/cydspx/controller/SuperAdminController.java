package cydspx.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.handler.AllocationHandler;
import cydspx.handler.SuperAdminHandler;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;

@Controller
public class SuperAdminController {
	@Autowired
	private SuperAdminHandler superAdminHandler;

	@Autowired
	private AllocationHandler allocationHandler;
	
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
		return superAdminHandler.getSchoolAdminAndExpertList(session);
	}

	@RequestMapping("/cydspx/superAdmin/deleteUser")
	@ResponseBody
	public ResponseMessage deleteUser(HttpSession session, @RequestParam int userId) 
	{
		return superAdminHandler.deleteUser(session, userId);
	}
	
	@RequestMapping("/cydspx/superAdmin/setUserPassword")
	@ResponseBody
	public ResponseMessage setUserPassword(HttpSession session, @RequestParam int userId, @RequestParam String newPassword)
	{
		return superAdminHandler.setUserPassword(session, userId, newPassword);
	}

	/*
	 * 分组
	 */
	@RequestMapping("/cydspx/superAdmin/allocation")
	@ResponseBody
	public ResponseMessage allocate(){
		return allocationHandler.allocation();
	}
	
	/*
	 * 设置分组规则，每组几个专家
	 */
	@RequestMapping("/cydspx/superAdmin/setgroupsize")
	@ResponseBody
	public ResponseMessage setGroupInfo(@RequestParam int groupSize){
		return allocationHandler.setGroupSize(groupSize);
	}
}











