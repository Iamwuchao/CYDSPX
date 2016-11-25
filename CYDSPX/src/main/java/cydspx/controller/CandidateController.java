package cydspx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.handler.CandidateHandler;
import cydspx.mode.ResponseMessage;

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
			@RequestParam String job,
			@RequestParam String title,
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
		return candidateHandler.addCandidate(session, name, sex, birthday, state, cert_type, cert_no, 
										photograph, nation, politics, edu_type, edu_hierarchy,
										subject_category, degree_type, academy_name, specialty_name,
										job, title, workunit, address, postal_code, mobile_phone,
										tel_phone, email, resume, origin_recommand);
	}
}
