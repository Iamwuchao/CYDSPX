package cydspx.mode;

import lombok.Data;

@Data
public class CandidateAbstract {
	private int id;//id
	private String name;//姓名
	private String workunit;//工作单位
	private String photograph;
	private String job;					//职务			17
	private String title;				//职称			18
	private String attachment;//简历附件
	private int score;//得分
}
