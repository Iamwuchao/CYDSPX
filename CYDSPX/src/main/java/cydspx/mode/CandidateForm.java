package cydspx.mode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CandidateForm extends Candidate{
	private String[] service_intention;			//服务意向
	private String[] vocations;					//行业
	private String achievement1;
	private String prize_year1;
	private String prize_level1;
	private String achievement2;
	private String prize_year2;
	private String prize_level2;
	private String achievement3;
	private String prize_year3;
	private String prize_level3;
	
	private String project_name1;
	private String elect_year1;
	private String elect_level1;
	private String project_name2;
	private String elect_year2;
	private String elect_level2;
	private String project_name3;
	private String elect_year3;
	private String elect_level3;
		
	
}
