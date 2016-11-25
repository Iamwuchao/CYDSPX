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
	
	public boolean addCandidate(
			String name,
			int sex,
			String birthday,
			String state,
			String cert_type,
			String cert_no,
			String photograph,
			String nation,
			String politics,
			String edu_type,
			String edu_hierarchy,
			String subject_category,
			String degree_type,
			String academy_name,
			String specialty_name,
			String job,
			String title,
			String workunit,
			String address,
			String postal_code,
			String mobile_phone,
			String tel_phone,
			String email,
			String resume,
			String origin_recommand) {
		return candidateMapper.addCandidate(name, sex, birthday, state, cert_type, cert_no, 
				photograph, nation, politics, edu_type, edu_hierarchy,
				subject_category, degree_type, academy_name, specialty_name,
				job, title, workunit, address, postal_code, mobile_phone,
				tel_phone, email, resume, origin_recommand);
	}
	
	public List<Candidate> getCandidatesOfSchool(String academy_name) {
		return candidateMapper.getCandidatesOfSchool(academy_name);
	}
}
