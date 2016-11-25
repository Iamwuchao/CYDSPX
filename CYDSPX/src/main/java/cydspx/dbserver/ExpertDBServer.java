package cydspx.dbserver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.AllocateMapper;
import cydspx.mapper.CandidateGroupMapper;
import cydspx.mapper.ElectResultMapper;
import cydspx.mapper.ExpertGroupMapper;
import cydspx.mode.CandidateAbstract;

@Service
public class ExpertDBServer {
	
	@Autowired
	private ExpertGroupMapper expertGroupMapper;
	
	@Autowired
	private CandidateGroupMapper candidateGroupMapper;
	
	@Autowired
	private AllocateMapper allocateMapper;
	
	@Autowired
	private ElectResultMapper electResultMapper;
	
	
	/*
	 * 返回指定专家的已评分的候选人列表 
	 */
	public List<CandidateAbstract> getGradedCandidateList(int expertId){
		return electResultMapper.getGradedCandidateList(expertId);
	}
	
	/*
	 * 返回专家所在的专家组ID
	 */
	public int getExpertGroupId(int expertId){
		return expertGroupMapper.getGroupIdByUserID(expertId);
	}
	
	
	public List<CandidateAbstract> getAllCandidateList(int groupId){
		return candidateGroupMapper.getAllCandidateBygroupId(groupId);
	}
	
	/*
	 * 返回指定专家的未评分的候选人列表
	 */
	public List<CandidateAbstract> getUngradedCandidateList(int expertId){
		List<CandidateAbstract> gradedList = electResultMapper.getGradedCandidateList(expertId);
		int groupId = getExpertGroupId(expertId);
		List<CandidateAbstract> allList = candidateGroupMapper.getAllCandidateBygroupId(groupId);
		
		Set<Integer> gradedIdSet = new HashSet<Integer>();
		for(CandidateAbstract candidate : gradedList){
			gradedIdSet.add(candidate.getId());
		}
		List<CandidateAbstract> ungradedCandidateList = new LinkedList<CandidateAbstract>();
		for(CandidateAbstract candidate : allList){
			if(gradedIdSet.contains(candidate.getId()))
				continue;
			else
				ungradedCandidateList.add(candidate);
		}
		return ungradedCandidateList;
	}
}