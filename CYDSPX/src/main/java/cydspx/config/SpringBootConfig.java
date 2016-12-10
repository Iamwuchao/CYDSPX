package cydspx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cydspx.interceptor.Interceptor;

@Configuration
public class SpringBootConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private Interceptor interceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		
        registry.addInterceptor(interceptor).addPathPatterns("/**");
        
    }
}
