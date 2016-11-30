package cydspx.dbserver;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.CandidateGroupMapper;
import cydspx.mapper.CandidateMapper;
import cydspx.mode.Candidate;
import cydspx.mode.CandidateAbstract;


@Data
@Service
public class CandidateDBServer {
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Autowired
	private CandidateGroupMapper candidateGroupMapper;
	
	public int addCandidate(Candidate candidate) {
		candidateMapper.addCandidate(candidate);
		return candidate.getId();
	}
	
	public List<Candidate> getCandidatesOfSchool(String academy_name) {
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
	
	
}
