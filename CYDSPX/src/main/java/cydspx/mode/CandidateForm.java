package cydspx.mode;

import java.util.ArrayList;
import java.util.List;

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
	
	
	boolean check(String data)
	{
		if(data == null || data.isEmpty()) return false;
		return true;
	}
	
	boolean check(String[] data)
	{
		if(data == null || data.length == 0) return false;
		return true;
	}
	
	public List<String> check()
	{
		List<String> res = new ArrayList<String>();
		if(!check(this.photograph)) res.add("照片");
		if(!check(this.attachment)) res.add("优秀创新创业导师人才库导师推荐表");
		if(!check(this.name)) res.add("名称");
		if(!check(this.nation)) res.add("民族");
		if(!check(this.politics)) res.add("政治面貌");
		if(!check(this.cert_type)) res.add("证书类型");
//		if(!check(this.sex)) return false;
		if(!check(this.birthday)) res.add("生日");
		if(!check(this.state)) res.add("国家");
		if(!check(this.cert_no)) res.add("证件编号");
		if(!check(this.mobile_phone)) res.add("移动电话");
		if(!check(this.email))res.add("邮箱");
		if(!check(this.tel_phone)) res.add("固定电话");
		if(!check(this.job)) res.add("职务");
		if(!check(this.workunit)) res.add("工作单位");
		if(!check(this.origin_recommand)) res.add("原始推荐单位");
		if(!check(this.service_intention)) res.add("服务意向");
		if(!check(this.vocations)) res.add("行业");
		
		return res;
	}
	
}
