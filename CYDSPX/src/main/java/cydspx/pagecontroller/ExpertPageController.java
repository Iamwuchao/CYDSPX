package cydspx.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpertPageController {
	@RequestMapping("/cydspx/notgradecandidatetable")
	public String ungradedcandidatetable(){
		System.out.println("/cydspx/notgradecandidatetable");
		return "/expert/ungradedcandidatetable";
	}
	
	@RequestMapping("/cydspx/gradedcandidatettable")
	public String gradedcandidatetable(){
		System.out.println("/cydspx/gradedcandidatettable");
		return "/expert/gradedcandidatetable";
	}
	
	@RequestMapping("/cydspx/schoolAdmin/showcandidate")
	public String showCandidate(){
		return "/expert/showCandidateinfo";
	}
	
	
	@RequestMapping("/cydspx/expert/passwordchange")
	public String passWordChange(){
		return "/expert/passwordChange";
	}
}
