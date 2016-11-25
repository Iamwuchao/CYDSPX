package cydspx.dbserver;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.CandidateMapper;
import cydspx.mode.Candidate;


@Data
@Service
public class CandidateDBServer {
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	public boolean addCandidate(Candidate c) {
		return candidateMapper.addCandidate(c);
	}
	
	public List<Candidate> getCandidatesOfSchool(String academy_name) {
		return candidateMapper.getCandidatesOfSchool(academy_name);
	}
}
