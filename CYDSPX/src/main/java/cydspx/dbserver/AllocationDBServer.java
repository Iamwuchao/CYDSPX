package cydspx.dbserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.AllocateMapper;

@Service
public class AllocationDBServer {

	@Autowired
	private AllocateMapper allocationMapper;
	
	public void saveGroupInfo(int candidateGroupId,int expertGroupId){
		allocationMapper.saveGroupInfo(candidateGroupId, expertGroupId);
	}
}
