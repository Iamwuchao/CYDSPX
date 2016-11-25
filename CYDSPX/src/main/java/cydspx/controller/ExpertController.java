package cydspx.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cydspx.globalInfo.SessionKey;
import cydspx.handler.ExpertHandler;
import cydspx.mode.CandidateAbstract;
import cydspx.mode.User;

@Controller
public class ExpertController {
	
	@Autowired
	private ExpertHandler expertHandler;
	
	@RequestMapping("/cydspx/ungradedcandidatelist")
	@ResponseBody
	public List<CandidateAbstract> getUngradedcandidateList(HttpSession session){
		User user = (User) session.getAttribute(SessionKey.USER_INFO.name());
		if(user == null) return new LinkedList<CandidateAbstract>();
		return expertHandler.getUngradedCandidateList(user);
	}
}
