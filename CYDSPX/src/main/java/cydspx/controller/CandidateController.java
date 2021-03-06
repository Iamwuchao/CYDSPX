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
import cydspx.mode.CandidateDataMessage;
import cydspx.mode.CandidateForm;
import cydspx.mode.ElectJoin;
import cydspx.mode.Prize;
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
  		List<String> notInputFileds = form.check();
  		if(!notInputFileds.isEmpty())
  		{
  			ResponseMessage msg = new ResponseMessage();
  			msg.setCode(ResponseCode.FAIL.ordinal());
  			msg.setMessage("有必填项" + notInputFileds + " 未填写，请继续填写");
  			return msg;
  		}
  		
  		User user = (User)session.getAttribute(SessionKey.USER_INFO.name());
  		
  		int userId = user.getUserId();
  		
  		int candidate_id = candidateHandler.addCandidate(session, form,userId);
  		
  		/*
  		 * 行业
  		 */
  		if (form.getVocation() != null) {
  			for (String vocation : form.getVocation()) {
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
  		msg.setCode(ResponseCode.SUCCESS.ordinal());
  		msg.setMessage("提交成功");
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
	
	
	
	
	@RequestMapping("/cydspx/getCandidate")
	@ResponseBody
	public CandidateDataMessage getCandidateList(HttpSession session,@RequestParam int candidate_id){
		CandidateDataMessage msg = new CandidateDataMessage();
		msg.candidate = candidateHandler.getCandidate(candidate_id);
		String photograph = msg.candidate.getPhotograph();
		if(!photograph.endsWith("/"))
		{
			photograph+="/";
			msg.candidate.setPhotograph(photograph);
		}
		
		List<Integer> electjoin_ids = relationHandler.getElectJoinIds(candidate_id);
		List<Integer> prize_ids = relationHandler.getPrizeIds(candidate_id);
		
		msg.prizes = new LinkedList<Prize>();
		msg.electjoins = new LinkedList<ElectJoin>();
		if(prize_ids!=null && prize_ids.size()>0)
			msg.prizes = getPrizesByIds(session,prize_ids);
		if(electjoin_ids!=null && electjoin_ids.size()>0)
			msg.electjoins = getElectJoinsByids(session,electjoin_ids);
		
		while(msg.prizes.size()<3){
			msg.prizes.add(new Prize());
		}
		
		while(msg.electjoins.size()<3){
			msg.electjoins.add(new ElectJoin());
		}
		
		msg.services = relationHandler.getServices(candidate_id);
		msg.vocations = relationHandler.getVocations(candidate_id);
		System.out.println("################################");
		System.out.println(msg);
		return msg;
	}
	
	@RequestMapping("/cydspx/getPrizesByIds")
	@ResponseBody
	public List<Prize> getPrizesByIds(HttpSession session, List<Integer> prize_ids) {
		return relationHandler.getPrizesByIds(prize_ids);
	}
	
	@RequestMapping("/cydspx/getElectJoinsByids")
	@ResponseBody
	public List<ElectJoin> getElectJoinsByids(HttpSession session, List<Integer> electjoin_ids) {
		return relationHandler.getElectJoinsByIds(electjoin_ids);
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
	
	//删除指定用户
	@RequestMapping("/cydspx/candidate/removecandidate")
	@ResponseBody
	public ResponseMessage removeCandidate(@RequestParam int candidateId){
		return candidateHandler.removeCandidate(candidateId);
	}
	
	//更新用户信息
/*	@RequestMapping("/cydspx/candidate/editcandidate")
	@ResponseBody
	public ResponseMessage editCandidate(CandidateForm form){
		
	}*/
	
	//更新用户获奖信息
	@RequestMapping("/cydspx/candidate/addoneprize")
	@ResponseBody
	public ResponseMessage addPrize(@RequestParam int candidateId,@RequestParam String achievement,@RequestParam String year,@RequestParam String level){
		boolean result = relationHandler.addPrizeItem(candidateId, achievement, year, level);
		ResponseMessage response = new ResponseMessage();
		if(result){
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("fail");
		}
		else{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("success");
		}
		return response;
	}
	
	@RequestMapping("/cydspx/candidate/deleteprize")
	@ResponseBody
	public ResponseMessage deletePrize(@RequestParam int candidateId,@RequestParam int id){
		boolean result = relationHandler.deletePrizeJoinItem(candidateId, id);
		ResponseMessage response = new ResponseMessage();
		if(result){
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("fail");
		}
		else{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("success");
		}
		return response;
	}
	
	@RequestMapping("/cydspx/candidate/addoneelectjoin")
	@ResponseBody
	public ResponseMessage addOneelectJoin(@RequestParam int candidateId,@RequestParam String projectName,@RequestParam String electYear,String level){
		boolean result = relationHandler.addElectJoinItem(candidateId, projectName, electYear, level);
		ResponseMessage response = new ResponseMessage();
		if(result){
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("fail");
		}
		else{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("success");
		}
		return response;
	}
	
	@RequestMapping("/cydspx/candidate/deleteelectjoin")
	@ResponseBody
	public ResponseMessage deleteelectJoin(@RequestParam int candidateId,@RequestParam int id){
		boolean result = relationHandler.deleteElectJoinItem(candidateId, id);
		ResponseMessage response = new ResponseMessage();
		if(result){
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("fail");
		}
		else{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("success");
		}
		return response;
	}
	
	@RequestMapping("/cydspx/candidate/updatecandidate")
	@ResponseBody
	public ResponseMessage updateCandidate(HttpServletRequest request, HttpSession session, CandidateForm form){
		System.out.println("photo "+form.getPhotograph());
  		System.out.println("attach "+form.getAttachment());
		List<String> notInputFileds = form.check();
  		if(!notInputFileds.isEmpty())
  		{
  			ResponseMessage msg = new ResponseMessage();
  			msg.setCode(ResponseCode.FAIL.ordinal());
  			msg.setMessage("有必填项" + notInputFileds + " 未填写，请继续填写");
  			return msg;
  		}
		
		candidateHandler.updateCandidate(form);
		
		int candidate_id = form.getId();
		
		
		/*
  		 * 行业
  		 */
  		if (form.getVocation() != null) {
  			for (String vocation : form.getVocation()) {
  				relationHandler.addVocationItem(candidate_id, vocation);
  			}
  		}
  		
  		/*
  		 * 服务意向
  		 */
  		if (form.getService_intention() != null) {
  			//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
  			for (String service : form.getService_intention()) {
  				//System.out.println("service "+service);
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
  		msg.setCode(ResponseCode.SUCCESS.ordinal());
  		msg.setMessage("修改成功");
  		return msg;
		
	}
	
	
}
