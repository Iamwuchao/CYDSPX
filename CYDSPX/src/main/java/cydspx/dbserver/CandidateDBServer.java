package cydspx.dbserver;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.CandidateGroupMapper;
import cydspx.mapper.CandidateMapper;
import cydspx.mapper.ElectResultMapper;
import cydspx.mode.Candidate;
import cydspx.mode.CandidateAbstract;


@Data
@Service
public class CandidateDBServer {
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Autowired
	private ElectResultMapper electResultMapper;
	
	@Autowired
	private CandidateGroupMapper candidateGroupMapper;
	
	public int addCandidate(Candidate candidate) {
		candidateMapper.addCandidate(candidate);
		return candidate.getId();
	}
	
	public List<Candidate> getCandidatesOfSchool(String academy_name) {
		System.out.println(academy_name);
		return candidateMapper.getCandidatesOfSchool(academy_name);
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
}
