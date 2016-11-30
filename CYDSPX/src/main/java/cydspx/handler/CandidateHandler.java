package cydspx.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.CandidateDBServer;
import cydspx.mode.ResponseMessage;
import cydspx.mode.Candidate;
import cydspx.mode.CandidateAbstract;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class CandidateHandler {
	
	@Autowired
	private CandidateDBServer candidateDBServer;
	
	private static final AtomicInteger finish= new AtomicInteger(0);//是否已经完成所有候选人分数的计算
	
	public ResponseMessage addCandidate(HttpSession session, String name, int sex, String birthday, String state, String cert_type, String cert_no, 
										String photograph, String nation, String politics, String edu_type, String edu_hierarchy,
										String subject_category, String degree_type, String academy_name, String specialty_name,
										String job, String title, String workunit, String address, String postal_code, String mobile_phone,
										String tel_phone, String email, String resume, String origin_recommand) {
		return null;
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
