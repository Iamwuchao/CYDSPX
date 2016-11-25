package cydspx.handler.relation;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.dbserver.relation.CandidateVocationDBServer;
import cydspx.mapper.relation.CandidateServiceRelationMapper;
import cydspx.mode.ResponseMessage;


@Data
@Service
public class CandidateVocationHandler {

	@Autowired
	private CandidateVocationDBServer candidatevocationeMapper;
	
	public ResponseMessage addServiceItem(int candidate_id, String vocation) {
		ResponseMessage response = new ResponseMessage();
		try {
			candidatevocationeMapper.addVocationItem(candidate_id, vocation);
			response.setMessage("生成成功！");
		} catch (Exception e) {
			response.setMessage("数据库错误");
		}
		return response;
	}
}