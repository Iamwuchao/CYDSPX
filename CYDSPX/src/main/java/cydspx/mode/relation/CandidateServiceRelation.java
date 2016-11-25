package cydspx.mode.relation;

import lombok.Data;

@Data
public class CandidateServiceRelation {
	private int id;
	private int candidate_id;
	private String service;
}
