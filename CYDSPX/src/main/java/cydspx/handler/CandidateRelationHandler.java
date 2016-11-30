package cydspx.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.CandidateRelationDBServer;
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
	
}
