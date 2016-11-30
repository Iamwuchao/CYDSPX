package cydspx.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * 主要用于处理附件 上传下载
 */
@Controller
@Slf4j
public class AttachmentController {
	
	@Autowired
	private Environment env;
	
	/*
	 * 获取附件
	 */
	 @RequestMapping(value="/cydspx/attachment/{filename}")
	 public ResponseEntity<InputStreamResource> getDocument(@PathVariable String filename,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/* 	String path = env.getProperty("rootPath");
	    	log.info("path = " + path);
	    	try {
				StreamUtils.copy( new FileInputStream(path + filename) , response.getOutputStream() );
			}catch (IOException e) {
				log.warn("StreamUtils copy warn",e );
			}
	    	
	    	try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				log.warn("getOutputStream flush warn",e );
			}*/
		 String path = env.getProperty("rootPath") + filename;
		 FileSystemResource file=new FileSystemResource(path);
	    	if(!file.exists()){
	    		System.out.println("文件不存在");
	    	}
	    	HttpHeaders headers = new HttpHeaders();  
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
	        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));  
	        headers.add("Pragma", "no-cache");  
	        headers.add("Expires", "0");  
	        return ResponseEntity  
	                .ok()  
	                .headers(headers)  
	                .contentLength(file.contentLength())  
	                .contentType(MediaType.parseMediaType("application/octet-stream"))  
	                .body(new InputStreamResource(file.getInputStream())); 
	 }
	 
}
