package cydspx.pagecontroller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cydspx.globalInfo.SessionKey;
import cydspx.globalInfo.UserType;
import cydspx.mode.User;

@Controller
public class PageController {
	
	@RequestMapping("/cydspx")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping("/cydspx/homepage")
	public String showHomepage(HttpSession session){
		User user = (User) session.getAttribute(SessionKey.USER_INFO.name());
		if(user == null) return "login";
		if(user.getUserType()==UserType.EXPERT.ordinal()){
			return "/expert/expert";
		}
		else if(user.getUserType()==UserType.SCHOOLADMIN.ordinal()){
			return "/schooladmin/addCandidate";
		}
		else if(user.getUserType()==UserType.SUPERADMIN.ordinal()){
			return "/superAdmin/superAdmin";
		}
		else{
			return "/cydspx";
		}		
	}
	
	@RequestMapping("/cydspx/superAdmin/getPage")
	public String getSuperAdminPage( @RequestParam String pageName)
	{
		System.out.println("hhhhhhhhhhh");
		return "/superAdmin/" + pageName;
	}
	
	
	@RequestMapping("/cydspx/schoolAdmin/addCandidate")
	public String addCandidatePage()
	{
		return "/schoolAdmin/addCandidate";
	}
	
	@RequestMapping("/cydspx/schoolAdmin/checkCandidate")
	public String checkCandidatePage()
	{
		return "/schoolAdmin/checkCandidate";
	}
	
	
	
	@RequestMapping("/cydspx/schoolAdmin/passwordChange")
	public String passwordChange()
	{
		return "/schoolAdmin/passwordChange";
	}
	
}
