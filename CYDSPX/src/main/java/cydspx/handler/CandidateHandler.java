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
import cydspx.dbserver.SchoolAdminDBServer;
import cydspx.globalInfo.ResponseCode;
import cydspx.globalInfo.UserType;
import cydspx.mode.ResponseMessage;
import cydspx.mode.Candidate;
import cydspx.mode.User;
import cydspx.mode.CandidateAbstract;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class CandidateHandler {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CandidateDBServer candidateDBServer;
	
	@Autowired
	private SchoolAdminDBServer schoolAdminDBServer;
	
	private static final AtomicInteger finish= new AtomicInteger(0);//是否已经完成所有候选人分数的计算
	
	public int addCandidate(HttpSession session, Candidate candidate,int userId) {
		return candidateDBServer.addCandidate(candidate,userId);
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
		int userId = user.getUserId();
		return candidateDBServer.getCandidatesOfSchool(userId);
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
	
	//上传汇总表
	public ResponseMessage saveSummarize(int userId, String fileDir){
		ResponseMessage response = new ResponseMessage();
		response = schoolAdminDBServer.saveSummarize(userId, fileDir);
		return response;
	}
	
	public Candidate getCandidate(int candidate_id) {
		return candidateDBServer.getCandidate(candidate_id);
	}

	/*
	 * 删除指定候选人
	 */
	public ResponseMessage removeCandidate(int candidateId){
		try{
			int rows = candidateDBServer.removeCandidate(candidateId);
			ResponseMessage response = new ResponseMessage();
			if(rows>0){
				response.setCode(ResponseCode.SUCCESS.ordinal());
				response.setMessage("删除成功");
			}
			else
			{
				response.setCode(ResponseCode.FAIL.ordinal());
				response.setMessage("删除失败");
			}
			return response;
		}catch(Exception e){
			ResponseMessage response = new ResponseMessage();
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("删除失败");
			return response;
		}
	}
	
	public void updateCandidate(Candidate candidate){
		candidateDBServer.updateCandidate(candidate);
		candidateDBServer.clearCandidaterelation(candidate.getId());
	}
}
