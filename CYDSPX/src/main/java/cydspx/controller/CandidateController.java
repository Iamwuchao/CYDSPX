package cydspx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;
import cydspx.globalInfo.Const;
import cydspx.handler.CandidateHandler;
import cydspx.handler.CandidateRelationHandler;
import cydspx.mode.ResponseMessage;
import cydspx.mode.CandidateForm;

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
		return msg;
	}
	
	@Autowired
	private CandidateHandler candidateHandler;
	@Autowired
	private CandidateRelationHandler relationHandler;
	
	@RequestMapping("/cydspx/candidate/addCandidate")
	@ResponseBody
	public ResponseMessage addCandidate(HttpServletRequest request, HttpSession session, CandidateForm form) {
		
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
}
