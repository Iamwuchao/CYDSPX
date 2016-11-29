package cydspx.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cydspx.handler.CandidateHandler;
import cydspx.mode.ResponseMessage;




/*
 * 主要用于处理附件 上传下载
 */
@Controller
@Slf4j
public class AttachmentController {
	
	@Autowired
	private Environment env;
	
	//wl
	@Autowired
	private CandidateHandler candidateHandler;
	
	/*
	 * 获取附件
	 */
	 @RequestMapping(value="/cydspx/attachment/{filename}")
	 public void getDocument(@PathVariable String filename,HttpServletRequest request,HttpServletResponse response){
		 	String path = env.getProperty("rootPath");
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
			}
	 }
	 //上传头像
	 @RequestMapping("/cydspx/attachmentpic")
	 @ResponseBody
	 public ResponseMessage getAttachementPic(@RequestParam("attachement_pic") MultipartFile file) throws IOException {
		 ResponseMessage response = new ResponseMessage();
		 String fileName = file.getOriginalFilename();
		 System.out.println("filename:::"+fileName);
		//返回来的是存储的路径
		String filename = candidateHandler.uploadFile(file, fileName);
		String path = env.getProperty("rootPath") + filename;
		response.setMessage(filename);
		return response;
	}
	 //上传附加
	 @RequestMapping("/cydspx/attachmentfile")
	 @ResponseBody
	 public ResponseMessage getAttachementFile(@RequestParam("attachement_file") MultipartFile file) throws IOException {
		 ResponseMessage response = new ResponseMessage();
		 String fileName = file.getOriginalFilename();
		 System.out.println("filename:::"+fileName);
		//返回来的是存储的路径
		String filename = candidateHandler.uploadFile(file, fileName);
		String path = env.getProperty("rootPath") + filename;
		response.setMessage(filename);
		return response;
	}
	 
}
