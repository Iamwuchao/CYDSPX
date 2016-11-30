package cydspx.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;
import cydspx.globalInfo.Const;
import cydspx.globalInfo.SessionKey;
import cydspx.handler.CandidateHandler;
import cydspx.mode.Candidate;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;

@Controller
public class CandidateController {
	
	@Autowired
	private CandidateHandler candidateHandler;

	@RequestMapping("/cydspx/candidate/addCandidate")
	@ResponseBody
	public ResponseMessage addUserPage(HttpSession session,
			@RequestParam String name,
			@RequestParam int sex,
			@RequestParam String birthday,
			@RequestParam String state,
			@RequestParam String cert_type,
			@RequestParam String cert_no,
			@RequestParam String photograph,
			@RequestParam String nation,
			@RequestParam String politics,
			@RequestParam String edu_type,
			@RequestParam String edu_hierarchy,
			@RequestParam String subject_category,
			@RequestParam String degree_type,
			@RequestParam String academy_name,
			@RequestParam String specialty_name,
			@RequestParam List<String> vocations,
			@RequestParam String job,
			@RequestParam String title,
			@RequestParam List<String> services,
			@RequestParam String workunit,
			@RequestParam String address,
			@RequestParam String postal_code,
			@RequestParam String mobile_phone,
			@RequestParam String tel_phone,
			@RequestParam String email,
			@RequestParam String resume,
			@RequestParam String origin_recommand
			) 
	{
		System.out.println("haha");
		return candidateHandler.addCandidate(session, name, sex, birthday, state, cert_type, cert_no, 
										photograph, nation, politics, edu_type, edu_hierarchy,
										subject_category, degree_type, academy_name, specialty_name,
										job, title, workunit, address, postal_code, mobile_phone,
										tel_phone, email, resume, origin_recommand);
	}
	
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
	
	
	//wl
	@RequestMapping("/cydspx/getCandidateList")
	@ResponseBody
	public List<Candidate> getCandidateList(HttpSession session){
		User user = (User) session.getAttribute(SessionKey.USER_INFO.name());
		System.out.println("#####");
		System.out.println(user);
		if(user == null) return new LinkedList<Candidate>();
		return candidateHandler.getCandidateList(user);
	}
}
