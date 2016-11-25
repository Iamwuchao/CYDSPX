package cydspx.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cydspx.dbserver.CandidateDBServer;
import cydspx.mode.ResponseMessage;
import cydspx.mode.Candidate;


@Component
public class CandidateHandler {
	
	private CandidateDBServer candidateDBServer;
	
	public ResponseMessage addCandidate(HttpSession session, String name, int sex, String birthday, String state, String cert_type, String cert_no, 
										String photograph, String nation, String politics, String edu_type, String edu_hierarchy,
										String subject_category, String degree_type, String academy_name, String specialty_name,
										String job, String title, String workunit, String address, String postal_code, String mobile_phone,
										String tel_phone, String email, String resume, String origin_recommand) {
		return null;
	}

}
