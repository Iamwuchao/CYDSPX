package cydspx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cydspx.globalInfo.SessionKey;
import cydspx.mode.User;

@Service
public class Interceptor implements HandlerInterceptor{

	private static String[] passUrls = {"/cydspx","/cydspx/login"};
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
	        String uri = request.getRequestURI();
	       
	        HttpSession session = request.getSession();
	       // log.info("interceptor info: {}",String.format("请求参数, url: %s, method: %s, uri: %s, params: %s", url, method, uri, queryString));
	       User user = (User) session.getAttribute(SessionKey.USER_INFO.name());
	       if(user!=null) return true;
	       System.out.println("###### "+uri);
	        for(String passurl:passUrls){
	        	if(uri.equals(passurl)) return true;
	        }
	        
	        System.out.println("&&&&&& "+request.getContextPath() + "/");
	        response.sendRedirect("/cydspx");
	        return false;
	}

}
