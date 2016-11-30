package cydspx.dbserver;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.ElectJoinMapper;
import cydspx.mapper.PrizeMapper;
import cydspx.mapper.relation.CandidateServiceRelationMapper;
import cydspx.mapper.relation.CandidateVocationRelationMapper;

@Data
@Service
public class CandidateRelationDBServer {
	@Autowired
	private CandidateServiceRelationMapper candidateserviceMapper;
	
	public int addServiceItem(int candidate_id, String service) {
		return candidateserviceMapper.addServiceItem(candidate_id, service);
	}
	
	@Autowired
	private CandidateVocationRelationMapper candidatevocationMapper;
	
	public int addVocationItem(int candidate_id, String vocation) {
		return candidatevocationMapper.addVocationItem(candidate_id, vocation);
	}
	
	@Autowired
	private PrizeMapper prizeMapper;
	
	public int addPrizeItem(int candidate_id, String achievement, String prize_year, String level) {
		return prizeMapper.addPrizeItem(candidate_id, achievement, prize_year, level);
	}
	
	@Autowired
	private ElectJoinMapper electJoinMapper;
	
	public int addElectJoin(int candidate_id, String project_name, String elect_year, String level) {
		return electJoinMapper.addElectJoinItem(candidate_id, project_name, elect_year, level);
	}
}
