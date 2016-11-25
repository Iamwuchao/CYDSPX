package cydspx.handler.relation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import cydspx.dbserver.relation.CandidateServiceDBServer;
import cydspx.mode.ResponseMessage;

public class CandidateServiceHandler {
	
	@Autowired
	private CandidateServiceDBServer candidateServiceDBServer;
	
	public ResponseMessage addServiceItem(HttpSession session, int candidate_id, String service) {
		ResponseMessage response = new ResponseMessage();
		try {
			candidateServiceDBServer.addServiceItem(candidate_id, service);
			response.setMessage("生成成功！");
		} catch (Exception e) {
			response.setMessage("数据库错误");
		}
		return response;
	}
}
