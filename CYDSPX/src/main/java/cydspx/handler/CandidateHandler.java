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
import cydspx.mode.CandidateAbstract;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class CandidateHandler {
	//wl
	@Autowired
	private Environment env;
	
	@Autowired
	private CandidateDBServer candidateDBServer;
	
	private static final AtomicInteger finish= new AtomicInteger(0);//是否已经完成所有候选人分数的计算
	
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
	/*
	 * 获取指定数量的候选人列表
	 */
	public List<CandidateAbstract> getCandidateAbstractList(int count){
		
		if(count<0) return new LinkedList<CandidateAbstract>();
		computeCandidateScore();
		return candidateDBServer.getCandidateGroupList(count);
	}
	
	/*
	 * 计算候选人的平均分
	 */
	private int computeCandidateScore(){
		if(finish.compareAndSet(0, 1))
		{
			candidateDBServer.computeScore();
		}
		return 0;
	}

}
