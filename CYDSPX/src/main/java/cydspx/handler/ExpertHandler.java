package cydspx.handler;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.ExpertDBServer;
import cydspx.globalInfo.UserType;
import cydspx.mode.CandidateAbstract;
import cydspx.mode.User;



@Component
public class ExpertHandler {
	
	@Autowired
	private ExpertDBServer expertDBServer;
	
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
}
