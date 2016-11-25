package cydspx.mode;

import lombok.Data;

@Data
public class ElectJoin {
	private int id;
	private int candidate_id;
	private String project_name;
	private String elect_year;
	private String level;
}
