package cydspx.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpertPageController {

	
	@RequestMapping("/cydspx/judgeselecttable")
	public String expertjudgeSelectPage(){
		return "/expert/judgeselecttable";
	}
	
	@RequestMapping("/cydspx/notgradecandidatetable")
	public String ungradedcandidatetable(){
		return "/export/ungradedcandidatetable";
	}
	
	@RequestMapping("/cydspx/gradedcandidatettable")
	public String gradedcandidatetable(){
		return "/export/gradecandidatetable";
	}
	
}
