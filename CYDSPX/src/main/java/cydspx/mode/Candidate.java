package cydspx.mode;

import lombok.Data;

@Data
public class Candidate {
	private String name;	//姓名						1
	private int sex;		//性别						2
	private	String birthday;	//出生日期				3
	private String state;		//国家/地区				4
	private String cert_type;		//证件类型			5
	private String cert_no;			//证件号码			6
	private String photograph;		//照片				7
	private String nation;		//民族					8
	private String politics;		//政治面貌			9
	private String edu_type;		//学历类别			10
	private String edu_hierarchy;		//学历层次		11
	private String subject_category;		//学科门类	12
	private String degree_type;		//学位类别			13
	private String academy_name;		//院校名称		14
	private String specialty_name;		//专业名称		15
	//private String vocation;			//行业			16
	private String job;					//职务			17
	private String title;				//职称			18
	//private String service_intention;	//服务意向		19
	private String workunit;			//工作单位		20
	private String address;				//联系地址		21
	private String postal_code;			//邮政编码		22
	private String mobile_phone;		//移动电话		23
	private String tel_phone;			//固定电话		24
	private String email;				//电子邮件		25
	private String resume;				//工作简历		26
	private String origin_recommand;	//原始推荐单位	27
	private int prize_id;				//导师获奖信息
	private int elect_join_id;			//导师参评信息
}
