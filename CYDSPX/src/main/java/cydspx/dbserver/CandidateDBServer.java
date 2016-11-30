package cydspx.dbserver;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.CandidateGroupMapper;
import cydspx.mapper.CandidateMapper;
import cydspx.mode.Candidate;
import cydspx.mode.CandidateAbstract;


@Data
@Service
public class CandidateDBServer {
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Autowired
	private CandidateGroupMapper candidateGroupMapper;
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
		System.out.println(academy_name);
		return candidateMapper.getCandidatesOfSchool(academy_name);
	}
	
	public List<CandidateAbstract> getAllCandidates(){
		return candidateMapper.getAllCateAbstract();
	}
	
	public boolean containsCandidate(int groupId,int candidateId){
		int count = candidateGroupMapper.containCandidateOfGroup(groupId, candidateId);
		return count>0?true:false;
	}
	
	public boolean saveCandidateGroupInfo(int groupId,int candidateId){
		int rows =  candidateGroupMapper.insertCandidateGroupInfo(groupId, candidateId);
		return rows>0?true:false;
	}
	
	
}
