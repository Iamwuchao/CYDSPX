package cydspx.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpertPageController {
	@RequestMapping("/cydspx/notgradecandidatetable")
	public String ungradedcandidatetable(){
		return "/expert/ungradedcandidatetable";
	}
	
	@RequestMapping("/cydspx/gradedcandidatettable")
	public String gradedcandidatetable(){
		return "/expert/gradedcandidatetable";
	}
	
}
