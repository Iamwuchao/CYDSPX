package cydspx.dbserver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	//@Autowired
	//private AllocateMapper allocateMapper;
	
	@Autowired
	private ElectResultMapper electResultMapper;
	
	/*
	 * 返回指定专家的已评分的候选人列表 
	 */
	public List<CandidateAbstract> getGradedCandidateList(int expertId){
		List<CandidateAbstract> candidateList =  electResultMapper.getGradedCandidateList(expertId);
		for(CandidateAbstract candidate:candidateList){
			int score = electResultMapper.getScore(candidate.getId(), expertId);
			candidate.setScore(score);
		}
		return candidateList;
	}
	
	/*
	 * 返回专家所在的专家组ID
	 */
	public Integer getExpertGroupId(int expertId){
		return expertGroupMapper.getGroupIdByUserID(expertId);
	}
	
	
	public List<CandidateAbstract> getAllCandidateList(int groupId){
		return candidateGroupMapper.getAllCandidateBygroupId(groupId);
	}
	
	/*
	 * 返回指定专家的未评分的候选人列表
	 */
	public List<CandidateAbstract> getUngradedCandidateList(int expertId){
		System.out.println("@@@@@@@@@@@@@@@");
		List<CandidateAbstract> gradedList = electResultMapper.getGradedCandidateList(expertId);
		Integer groupId = getExpertGroupId(expertId);
		if(groupId == null) return new LinkedList<CandidateAbstract>();
		List<CandidateAbstract> allList = candidateGroupMapper.getAllCandidateBygroupId(groupId);
		
		System.out.println("+++++++++++ allList size "+allList.size());
		
		Set<Integer> gradedIdSet = new HashSet<Integer>();
		if(gradedList !=null){
			for(CandidateAbstract candidate : gradedList){
				gradedIdSet.add(candidate.getId());
				System.out.println("&&&&&&&&&&&&&& "+candidate.getId());
			}
		}
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		List<CandidateAbstract> ungradedCandidateList = new LinkedList<CandidateAbstract>();
		for(CandidateAbstract candidate : allList){
			if(gradedIdSet.contains(candidate.getId()))
				continue;
			else
				{
					System.out.println("未评分的候选人 "+candidate.getId());
					ungradedCandidateList.add(candidate);
				}
		}
		
		return ungradedCandidateList;
	}
	
	/*
	 * 保存专家分组信息
	 */
	public int addExpertGroupInfo(int expertId,int groupId){
		return expertGroupMapper.saveExpertGroupInfo(groupId, expertId);
	}
	
	/*
	 * 保存专家评分
	 */
	public int saveGradeInfo(int expertId,int candidateId,int score){
		return electResultMapper.saveGradeInfo(candidateId, expertId, score);
	}
}
