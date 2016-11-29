package cydspx.handler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cydspx.myutil.UUIGenerator;
import cydspx.dbserver.CandidateDBServer;
import cydspx.mode.ResponseMessage;
import cydspx.mode.Candidate;


@Component
public class CandidateHandler {
	//wl
	@Autowired
	private Environment env;
	
	private CandidateDBServer candidateDBServer;
	
	public ResponseMessage addCandidate(HttpSession session, String name, int sex, String birthday, String state, String cert_type, String cert_no, 
										String photograph, String nation, String politics, String edu_type, String edu_hierarchy,
										String subject_category, String degree_type, String academy_name, String specialty_name,
										String job, String title, String workunit, String address, String postal_code, String mobile_phone,
										String tel_phone, String email, String resume, String origin_recommand) {
		return null;
	}
	
	
	
	//wl
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

}
