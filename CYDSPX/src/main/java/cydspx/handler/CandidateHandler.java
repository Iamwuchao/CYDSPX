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
	
	@Autowired
	private CandidateDBServer candidateDBServer;
	
	public int addCandidate(HttpSession session, Candidate candidate) {
		return candidateDBServer.addCandidate(candidate);
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
