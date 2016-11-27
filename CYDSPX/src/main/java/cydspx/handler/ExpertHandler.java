package cydspx.handler;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.CandidateDBServer;
import cydspx.dbserver.ExpertDBServer;
import cydspx.globalInfo.ResponseCode;
import cydspx.globalInfo.UserType;
import cydspx.mode.CandidateAbstract;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;



@Component
public class ExpertHandler {
	
	@Autowired
	private ExpertDBServer expertDBServer;
	
	@Autowired
	private CandidateDBServer candidateDBServer;
	
	/*
	 * 获取某位专家的未评分的候选人列表
	 */
	public List<CandidateAbstract> getUngradedCandidateList(User user){
		if(user.getUserType()!= UserType.EXPERT.ordinal())
			return new LinkedList<CandidateAbstract>();
		
		return expertDBServer.getUngradedCandidateList(user.getUserId());
	}
	
	/*
	 * 获取某位专家的已评分的候选人列表
	 */
	public List<CandidateAbstract> getGradedCandidateList(User user){
		if(user.getUserType()!= UserType.EXPERT.ordinal())
			return new LinkedList<CandidateAbstract>();
		return expertDBServer.getGradedCandidateList(user.getUserId());
	}
	
	/*
	 * 打分，专家给某位候选人打分
	 */
	public ResponseMessage grade(User user,int candidateId,int score){
		
		ResponseMessage response = new ResponseMessage();
		//检查该专家是否有权为该候选人打分
		if(!check(user,candidateId)){
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("当前专家未被分配该候选人");
			return response;
		}
		
		expertDBServer.saveGradeInfo(user.getUserId(), candidateId, score);
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("评分成功");
		return response;
	}
	
	//检查该专家是否有权为该候选人打分
	private boolean check(User user,int candidateId){
		
		int groupId = expertDBServer.getExpertGroupId(user.getUserId());
		return candidateDBServer.containsCandidate(groupId, candidateId);
	}
}
