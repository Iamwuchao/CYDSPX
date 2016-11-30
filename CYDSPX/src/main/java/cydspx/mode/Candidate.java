package cydspx.mode;

import lombok.Data;

@Data
public class Candidate {
	protected int id = 0;
	protected String name = "";	//姓名							1
	protected int sex = 0;		//性别							2
	protected	String birthday = "";	//出生日期				3
	protected String state = "";		//国家/地区				4
	protected String cert_type = "";		//证件类型			5
	protected String cert_no = "";			//证件号码			6
	protected String photograph = "";		//照片				7
	protected String nation = "";		//民族					8
	protected String politics = "";		//政治面貌				9
	protected String edu_type = "";		//学历类别				10
	protected String edu_hierarchy = "";		//学历层次		11
	protected String subject_category = "";		//学科门类		12
	protected String degree_type = "";		//学位类别			13
	protected String academy_name = "";		//院校名称			14
	protected String specialty_name = "";		//专业名称		15
	//protected String vocation;			//行业				16
	protected String job = "";					//职务			17
	protected String title = "";				//职称			18
	//protected String service_intention;	//服务意向			19
	protected String workunit = "";			//工作单位			20
	protected String address = "";				//联系地址		21
	protected String postal_code = "";			//邮政编码		22
	protected String mobile_phone = "";		//移动电话			23
	protected String tel_phone = "";			//固定电话		24
	protected String email = "";				//电子邮件		25
	protected String resume = "";				//工作简历		26
	protected String origin_recommand = "";	//原始推荐单位		27
	//protected int prize_id;				//导师获奖信息
	//protected int elect_join_id;			//导师参评信息
}