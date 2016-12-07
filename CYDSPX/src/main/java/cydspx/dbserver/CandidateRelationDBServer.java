package cydspx.dbserver;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.ElectJoinMapper;
import cydspx.mapper.PrizeMapper;
import cydspx.mapper.relation.CandidateServiceRelationMapper;
import cydspx.mapper.relation.CandidateVocationRelationMapper;
import cydspx.mode.ElectJoin;
import cydspx.mode.Prize;

@Data
@Service
public class CandidateRelationDBServer {
	@Autowired
	private CandidateServiceRelationMapper candidateserviceMapper;
	
	public int addServiceItem(int candidate_id, String service) {
		return candidateserviceMapper.addServiceItem(candidate_id, service);
	}
	
	public List<String> getServices(int candidate_id) {
		return candidateserviceMapper.getElectJoin(candidate_id);
	}
	
	@Autowired
	private CandidateVocationRelationMapper candidatevocationMapper;
	
	public int addVocationItem(int candidate_id, String vocation) {
		return candidatevocationMapper.addVocationItem(candidate_id, vocation);
	}
	
	public List<String> getVocations(int candidate_id) {
		return candidatevocationMapper.getElectJoin(candidate_id);
	}
	
	@Autowired
	private PrizeMapper prizeMapper;
	
	public int addPrizeItem(int candidate_id, String achievement, String prize_year, String level) {
		return prizeMapper.addPrizeItem(candidate_id, achievement, prize_year, level);
	}
	
	public List<Integer> getPrizeIds(int candidate_id) {
		return prizeMapper.getPrizeIds(candidate_id);
	}
	
	public List<Prize> getPrizesByIds(List<Integer> prize_ids) {
		return prizeMapper.getPrizesByIds(prize_ids);
	}
	
	@Autowired
	private ElectJoinMapper electJoinMapper;
	
	public int addElectJoin(int candidate_id, String project_name, String elect_year, String level) {
		return electJoinMapper.addElectJoinItem(candidate_id, project_name, elect_year, level);
	}
	
	public List<Integer> getElectJoins(int candidate_id) {
		return electJoinMapper.getElectJoinIds(candidate_id);
	}
	
	public List<ElectJoin> getElectJoinByIds(List<Integer> electjoin_ids) {
		return electJoinMapper.getElectJoinByIds(electjoin_ids);
	}
}
