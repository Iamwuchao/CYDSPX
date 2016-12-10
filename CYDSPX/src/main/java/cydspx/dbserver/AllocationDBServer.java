package cydspx.dbserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.AllocateMapper;
import cydspx.mapper.CandidateGroupMapper;
import cydspx.mapper.ExpertGroupMapper;

@Service
public class AllocationDBServer {

	@Autowired
	private AllocateMapper allocationMapper;
	
	@Autowired
	private CandidateGroupMapper candidateGroupMapper;
	
	@Autowired
	private ExpertGroupMapper expertGroupMapper;
	
	public void saveGroupInfo(int candidateGroupId,int expertGroupId){
		allocationMapper.saveGroupInfo(candidateGroupId, expertGroupId);
	}
	
	public void clearTable(){
		//清空分组
		System.out.println("清空分组表数据");
		allocationMapper.truncateTable();
		candidateGroupMapper.truncateTable();
		expertGroupMapper.truncateTable();
	}
}
