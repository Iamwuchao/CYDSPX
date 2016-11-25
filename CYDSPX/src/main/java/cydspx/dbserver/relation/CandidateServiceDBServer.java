package cydspx.dbserver.relation;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.relation.CandidateServiceRelationMapper;

@Data
@Service
public class CandidateServiceDBServer {

	@Autowired
	private CandidateServiceRelationMapper candidateserviceMapper;
	
	public boolean addServiceItem(int candidate_id, String service) {
		return candidateserviceMapper.addServiceItem(candidate_id, service);
	}
}
