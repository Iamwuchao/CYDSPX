package cydspx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties
@SpringBootApplication
@MapperScan("cydspx.mapper")
public class ApplicationContext {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ApplicationContext.class, args);
	}

}
