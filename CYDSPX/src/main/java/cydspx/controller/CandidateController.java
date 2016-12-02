package cydspx.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.globalInfo.ResponseCode;
import lombok.Data;
import cydspx.globalInfo.Const;
import cydspx.globalInfo.SessionKey;
import cydspx.handler.CandidateHandler;
import cydspx.handler.CandidateRelationHandler;
import cydspx.handler.SuperAdminHandler;
import cydspx.mode.Candidate;
import cydspx.mode.CandidateForm;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;

@Controller
public class CandidateController {
	
	@Data
	class FormChoicesRespMessage {
		private String[] degree_types;
		private String[] services;
		private String[] subject_categories;
		private String[] titles;
		private String[] vocations;
		private String[] nations;
		private String[] stateList;
		private String[] edu_typeList;
		private String[] edu_hierarchyList;
	}
	
	@Autowired
	private SuperAdminHandler superAdminHandler;
	
 	@Autowired
  	private CandidateHandler candidateHandler;
  	@Autowired
  	private CandidateRelationHandler relationHandler;
  	
  	@RequestMapping("/cydspx/candidate/addCandidate")
  	@ResponseBody
  	public ResponseMessage addCandidate(HttpServletRequest request, HttpSession session, CandidateForm form) {
  		System.out.println("photo "+form.getPhotograph());
  		System.out.println("attach "+form.getAttachment());
  		int candidate_id = candidateHandler.addCandidate(session, form);
  		
  		/*
  		 * 行业
  		 */
  		if (form.getVocations() != null) {
  			for (String vocation : form.getVocations()) {
  				relationHandler.addVocationItem(candidate_id, vocation);
  			}
  		}
  		
  		/*
  		 * 服务意向
  		 */
  		if (form.getService_intention() != null) {
  			for (String service : form.getService_intention()) {
  				relationHandler.addServiceItem(candidate_id, service);
  			}
  		}
  		
  		
  		
  		/* 获奖和参评
  		 * so ugly, someone to refactor it
  		 */
  		if (form.getPrize_level1() != null) {
  			relationHandler.addPrizeItem(candidate_id, form.getAchievement1(), form.getPrize_year1(), form.getPrize_level1());
  		}
  		if (form.getElect_level1() != null) {
  			relationHandler.addElectJoinItem(candidate_id, form.getProject_name1(), form.getElect_year1(), form.getElect_level1());
  		}
  		
  		if (form.getPrize_level2() != null) {
  			relationHandler.addPrizeItem(candidate_id, form.getAchievement2(), form.getPrize_year2(), form.getPrize_level2());
  		}
  		if (form.getElect_level2() != null) {
  			relationHandler.addElectJoinItem(candidate_id, form.getProject_name2(), form.getElect_year2(), form.getElect_level2());
  		}
  		
  		if (form.getPrize_level3() != null) {
  			relationHandler.addPrizeItem(candidate_id, form.getAchievement3(), form.getPrize_year3(), form.getPrize_level3());
  		}
  		if (form.getElect_level3() != null) {
  			relationHandler.addElectJoinItem(candidate_id, form.getProject_name3(), form.getElect_year3(), form.getElect_level3());
  		}
  		ResponseMessage msg = new ResponseMessage();
  		return msg;
  	}
	
	@RequestMapping("/cydspx/candidate/getFormChoices")
	@ResponseBody
	public FormChoicesRespMessage addUserPage(HttpSession session) 
	{
		System.out.println("haha");
		FormChoicesRespMessage msg = new FormChoicesRespMessage();
		msg.degree_types = Const.degree_types;
		msg.services = Const.services;
		msg.subject_categories = Const.subject_categories;
		msg.titles = Const.titles;
		msg.vocations = Const.vocations;
		msg.nations = Const.nations;
		msg.stateList = Const.stateList;
		msg.edu_typeList = Const.edu_typeList;
		msg.edu_hierarchyList = Const.edu_hierarchyList;
		return msg;
	}
	
	
	//wl
	@RequestMapping("/cydspx/getCandidateList")
	@ResponseBody
	public List<Candidate> getCandidateList(HttpSession session){
		User user = (User) session.getAttribute(SessionKey.USER_INFO.name());
		if(user == null) return new LinkedList<Candidate>();
		return candidateHandler.getCandidateList(user);
	}
	
	//上传汇总表
	@RequestMapping(value = "/cydspx/saveSummarize", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage saveSummarize(HttpSession session, @RequestParam("fileDir") String fileDir ){
		User user = (User)session.getAttribute(SessionKey.USER_INFO.name());
		ResponseMessage response = new ResponseMessage();
		if(user == null){
			//没有登录的时候不能进行上传操作
			response.setCode(ResponseCode.FAIL.ordinal());
			return response;
		}
		response = candidateHandler.saveSummarize(user.getUserId(), fileDir);
		
		return response;
	}

	//xiugaimima
	@RequestMapping("/cydspx/candidate/changePassword")
	@ResponseBody
	public ResponseMessage changePassword(HttpSession session, @RequestParam String newPassword)
	{
		User user = (User) session.getAttribute(SessionKey.USER_INFO.name());
		if(user==null){
			ResponseMessage response = new ResponseMessage();	
			response.setMessage("请先登录！");
			return response;
			
		}
		int id=user.getUserId();
		return superAdminHandler.changePassword(id, newPassword);
	}
	
}
