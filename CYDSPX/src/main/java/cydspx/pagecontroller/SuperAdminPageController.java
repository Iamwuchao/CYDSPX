package cydspx.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 该类主要用于处理超级管理员的页面获取请求
 */
@Controller
public class SuperAdminPageController {
	
	/*
	 * 获取项目分配 信息设置页面
	 */
	@RequestMapping("/cydspx/allocationpage")
	public String getAllocationPage(){
		return "/superadmin/allocate";
	}
	
	/*
	 * 获取结果导出页面
	 */
	@RequestMapping("/cydspx/superadmin/resultpage")
	public String getResultpage(){
		return "/superadmin/result";
	}
	
}
