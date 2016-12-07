package cydspx.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.CandidateRelationDBServer;
import cydspx.mode.ElectJoin;
import cydspx.mode.Prize;
import cydspx.mode.ResponseMessage;

@Component
public class CandidateRelationHandler {
	@Autowired
	private CandidateRelationDBServer server;
	
	public boolean addServiceItem(int candidate_id, String service) {
		return server.addServiceItem(candidate_id, service) >= 1;
	}
	
	public boolean addVocationItem(int candidate_id, String vocation) {
		return server.addVocationItem(candidate_id, vocation) >= 1;
	}
	
	public boolean addPrizeItem(int candidate_id, String achievement, String prize_year, String level) {
		return server.addPrizeItem(candidate_id, achievement, prize_year, level) >= 1;
	}
	
	public boolean addElectJoinItem(int candidate_id, String project_name, String elect_year, String level) {
		return server.addElectJoin(candidate_id, project_name, elect_year, level) >= 1;
	}
	
	
	public List<String> getServices(int candidate_id) {
		return server.getServices(candidate_id);
	}
	
	public List<String> getVocations(int candidate_id) {
		return server.getVocations(candidate_id);
	}
	
	public List<Integer> getPrizeIds(int candidate_id) {
		return server.getPrizeIds(candidate_id);
	}
	
	public List<Prize> getPrizesByIds(List<Integer> prize_ids) {
		return server.getPrizesByIds(prize_ids);
	}
	
	public List<Integer> getElectJoinIds(int candidate_id) {
		return server.getElectJoins(candidate_id);
	}
	
	public List<ElectJoin> getElectJoinsByIds(List<Integer> electjoin_ids) {
		return server.getElectJoinByIds(electjoin_ids);
	}
	
}
