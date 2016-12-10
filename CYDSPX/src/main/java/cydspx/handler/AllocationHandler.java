package cydspx.handler;

import java.util.Iterator;
import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.AllocationDBServer;
import cydspx.dbserver.CandidateDBServer;
import cydspx.dbserver.ExpertDBServer;
import cydspx.dbserver.UserDBServer;
import cydspx.globalInfo.ResponseCode;
import cydspx.globalInfo.UserType;
import cydspx.mode.CandidateAbstract;
import cydspx.mode.ResponseMessage;
import cydspx.mode.User;

/*
 * 该类主要用于划分 专家组 和 候选人组
 * 将候选人组分配给专家组
 */
@Data
@Component
public class AllocationHandler {
	
	private static int groupSize =5;
	
	@Autowired
	private CandidateDBServer candidateDBServer;
	
	@Autowired
	private UserDBServer userDBServer;
	
	@Autowired
	private ExpertDBServer expertDBServer;
	
	@Autowired
	private AllocationDBServer allocationDBServer;
	
	/*
	 * 设置分组信息
	 */
	public ResponseMessage setGroupSize(int newGroupSize){
		ResponseMessage respnose = new ResponseMessage();
		groupSize = newGroupSize;
		respnose.setCode(ResponseCode.SUCCESS.ordinal());
		respnose.setMessage("设置成功");
		return respnose;
		
	}
	
	/*
	 * 这个函数用于分组
	 */
	public ResponseMessage allocation(){
		
		ResponseMessage response = new ResponseMessage();
		
		//清空之前的分组信息
		allocationDBServer.clearTable();
		
		//先对专家进行分组
		List<User> allExpert = userDBServer.getAllUsersByType(UserType.EXPERT.ordinal());
		
		if(allExpert!=null)
			System.out.println("############# allexpert "+allExpert.size());
		else
		{
			System.out.println("$$$$$ ");
		}
		if(groupSize<=0) {
			response.setCode(ResponseCode.FAIL.ordinal());
			response.setMessage("每个候选人至少需要超过一位专家评审，请重新设置评审规则");
			return response;
		}
		
		int groupCount = allExpert.size()/groupSize;
		
		//将专家分组信息写入数据库
		saveExpertGroupInfo(allExpert,groupCount,groupSize);
		
		//对候选人进行分组
		List<CandidateAbstract> allCandidateList= candidateDBServer.getAllCandidates();
		
		//将分组信息写入数据库
		saveCandidateGroupInfo(allCandidateList,groupCount);
		
		//将分组信息写入数据库
		for(int i=0;i<groupCount;i++){
			allocationDBServer.saveGroupInfo(i, i);
		}
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("分组成功");
		return response;
	}
	
	private void saveExpertGroupInfo(List<User> userList,int groupCount,int groupSize){
		
		Iterator<User> userIterator = userList.iterator();
		for(int groupId=0;groupId<groupCount;groupId++){
			for(int count=0;count<groupSize;count++){
					User user = userIterator.next();
					expertDBServer.addExpertGroupInfo(user.getUserId(), groupId);
			}
			if(groupId == (groupCount-1)){
				while(userIterator.hasNext()){
					User user = userIterator.next();
					expertDBServer.addExpertGroupInfo(user.getUserId(),groupId );
				}
			}
		}
	}
	
	private void saveCandidateGroupInfo(List<CandidateAbstract> candidateList,int groupCount){

		Iterator<CandidateAbstract> candidateIterator = candidateList.iterator();
		int groupSize = candidateList.size()/groupCount;
		for(int groupId=0;groupId<groupCount;groupId++){
			for(int count=0;count<groupSize;count++){
				CandidateAbstract candidate = candidateIterator.next();
				//candidateDBServer.(candidate.getId(), groupId);
				candidateDBServer.saveCandidateGroupInfo(groupId, candidate.getId());
			}
			if(groupId==(groupCount-1)){
				while(candidateIterator.hasNext()){
					CandidateAbstract candidate = candidateIterator.next();
					candidateDBServer.saveCandidateGroupInfo(groupId, candidate.getId());
				}
			}
		}
	}
}
