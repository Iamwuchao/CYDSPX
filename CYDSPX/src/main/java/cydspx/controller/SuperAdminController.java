package cydspx.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.globalInfo.ResponseCode;
import cydspx.handler.AllocationHandler;
import cydspx.handler.CandidateHandler;
import cydspx.handler.SuperAdminHandler;
import cydspx.mode.CandidateAbstract;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;
import cydspx.myutil.HTMLToWord;

@Controller
public class SuperAdminController {
	@Autowired
	private Environment env;
	
	@Autowired
	private SuperAdminHandler superAdminHandler;

	@Autowired
	private AllocationHandler allocationHandler;
	
	@Autowired
	private CandidateHandler candidateHandler;
	
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
	
	/*
	 * 获取评分结果列表
	 */
	@RequestMapping("/cydspx/superadmin/resultlist")
	@ResponseBody
	public List<CandidateAbstract>  getResultList(@RequestParam int count){
		return candidateHandler.getCandidateAbstractList(count);
	}
	
	/*
	 * 生成文档
	 */
	@RequestMapping("/cydspx/superadmin/generatedoc")
	@ResponseBody
	public ResponseMessage generateDoc(@RequestParam String htmlContent){
		 ResponseMessage response = new ResponseMessage();
			try {
				String rootPath = env.getProperty("rootPath");
				String fileName = System.currentTimeMillis()+".doc";
				HTMLToWord.createWord(rootPath,fileName,htmlContent);
				response.setCode(ResponseCode.SUCCESS.ordinal());
				response.setMessage(fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				response.setCode(ResponseCode.FAIL.ordinal());
				response.setMessage("生成word文档失败");
			}
		return response;
	}
	
	/*
	 * 返回未完成评分
	 */
	
	
}











