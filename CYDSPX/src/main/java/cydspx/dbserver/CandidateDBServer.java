package cydspx.dbserver;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cydspx.mapper.CandidateGroupMapper;
import cydspx.mapper.CandidateMapper;
import cydspx.mapper.ElectJoinMapper;
import cydspx.mapper.ElectResultMapper;
import cydspx.mapper.PrizeMapper;
import cydspx.mapper.relation.CandidateServiceRelationMapper;
import cydspx.mapper.relation.CandidateVocationRelationMapper;
import cydspx.mode.Candidate;
import cydspx.mode.CandidateAbstract;
import cydspx.mode.CandidateDataMessage;


@Data
@Service
public class CandidateDBServer {
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Autowired
	private ElectResultMapper electResultMapper;
	
	@Autowired
	private CandidateGroupMapper candidateGroupMapper;
	
	@Autowired
	private ElectJoinMapper electJoinMapper;
	
	@Autowired
	private CandidateServiceRelationMapper candidateServiceRelatioinMapper;
	
	@Autowired
	private CandidateVocationRelationMapper candidateVocationRelationMapper;
	
	
	@Autowired
	private PrizeMapper prizeMapper;
	
	public int addCandidate(Candidate candidate,int userId) {
		candidateMapper.addCandidate(candidate,userId);
		return candidate.getId();
	}
	
	public List<Candidate> getCandidatesOfSchool(int userId) {
		System.out.println(userId);
		return candidateMapper.getCandidatesOfSchool(userId);
	}
	
	public List<CandidateAbstract> getAllCandidates(){
		return candidateMapper.getAllCateAbstract();
	}
	
	public boolean containsCandidate(int groupId,int candidateId){
		int count = candidateGroupMapper.containCandidateOfGroup(groupId, candidateId);
		return count>0?true:false;
	}
	
	public boolean saveCandidateGroupInfo(int groupId,int candidateId){
		int rows =  candidateGroupMapper.insertCandidateGroupInfo(groupId, candidateId);
		return rows>0?true:false;
	}
	
	public List<CandidateAbstract> getCandidateGroupList(int count){
		return candidateMapper.getCateAbstractList(count);
	}
	
	public void computeScore()
	{
		List<Integer> idlist = candidateMapper.getAllCandidateId();
		for(int id:idlist){
			int score = electResultMapper.getSumScore(id);
			int count = electResultMapper.getCountJudgement(id);
			if(count==0) count=1;
			double avgScore = ((score*1.0)/count*1.0);
			candidateMapper.updateCandidateScore(avgScore, id);
		}
	}

	/*
	 * 删除指定候选人
	 */
	@Transactional
	public int removeCandidate(int candidateId){
		int rows1 = candidateMapper.deleteCandidate(candidateId);
		int rows2 = electJoinMapper.removeElectJoinItem(candidateId);
		int rows3 = candidateVocationRelationMapper.deleteVocationItem(candidateId);
		int rows4 = candidateServiceRelatioinMapper.deleteCandidateServiceRelation(candidateId);
		prizeMapper.removePrize(candidateId);
		return rows1+rows2+rows3+rows4;//毫无道理的代码
	}

	public Candidate getCandidate(int candidate_id) {
		return candidateMapper.getCandidateByID(candidate_id);
	
	}
	
	
	public void updateCandidate(Candidate candidate){
		System.out.println("####### NAME ### "+candidate.getName()+"   $$$$  "+candidate.getId());
		candidateMapper.updateCandidate(candidate);
	}
	
	public void clearCandidaterelation(int candidateId){
		
		int rows2 = electJoinMapper.removeElectJoinItem(candidateId);
		int rows3 = candidateVocationRelationMapper.deleteVocationItem(candidateId);
		int rows4 = candidateServiceRelatioinMapper.deleteCandidateServiceRelation(candidateId);
		prizeMapper.removePrize(candidateId);
	}
	
}
