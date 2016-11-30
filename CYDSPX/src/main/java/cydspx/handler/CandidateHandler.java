package cydspx.handler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cydspx.myutil.UUIGenerator;
import cydspx.dbserver.CandidateDBServer;
import cydspx.globalInfo.UserType;
import cydspx.mode.ResponseMessage;
import cydspx.mode.Candidate;
import cydspx.mode.User;


@Component
public class CandidateHandler {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CandidateDBServer candidateDBServer;
	
	public ResponseMessage addCandidate(HttpSession session, String name, int sex, String birthday, String state, String cert_type, String cert_no, 
										String photograph, String nation, String politics, String edu_type, String edu_hierarchy,
										String subject_category, String degree_type, String academy_name, String specialty_name,
										String job, String title, String workunit, String address, String postal_code, String mobile_phone,
										String tel_phone, String email, String resume, String origin_recommand) {
		return null;
	}
	
	
	
	//wl 文件上传
	public String uploadFile(MultipartFile file,String fileName) throws IOException {
		String path = env.getProperty("rootPath");
    	String uuid = UUIGenerator.getUUID();
		String newFileName =  uuid+"."+getFileType(fileName);
		File newfile = new File(path, newFileName);
		FileOutputStream newfileoutputsteam = new FileOutputStream(newfile);
		try(BufferedOutputStream out = new BufferedOutputStream(newfileoutputsteam)){
			out.write(file.getBytes());
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return newFileName;
	}

	private String getFileType(String filename){
	   if ((filename != null) && (filename.length() > 0)) {   
	        int dot = filename.lastIndexOf('.');   
	        if ((dot >-1) && (dot < (filename.length() - 1))) {   
	            return filename.substring(dot + 1);   
	        }   
	    }   
	    return filename;   
	}
	
	/*
	 * 获取某位专家的未评分的候选人列表
	 */
	public List<Candidate> getCandidateList(User user){
		if(user.getUserType()!= UserType.SCHOOLADMIN.ordinal())
			return new LinkedList<Candidate>();
		String school = user.getSchool();
		System.out.println("school::"+school);
		return candidateDBServer.getCandidatesOfSchool(school); 
	}

}
