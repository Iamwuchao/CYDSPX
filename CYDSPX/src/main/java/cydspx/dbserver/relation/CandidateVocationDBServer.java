package cydspx.dbserver.relation;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.relation.CandidateVocationRelationMapper;

@Data
@Service
public class CandidateVocationDBServer {

	@Autowired
	private CandidateVocationRelationMapper candidatevocationMapper;
	
	public boolean addVocationItem(int candidate_id, String vocation) {
		return candidatevocationMapper.addVocationItem(candidate_id, vocation);
	}
}
