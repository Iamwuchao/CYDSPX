package cydspx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.type.JdbcType;

import cydspx.mode.Candidate;
import cydspx.mode.CandidateAbstract;

public interface CandidateMapper {
	@Results({
		@Result(property="id",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="name",column="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="sex",column="sex",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="birthday",column="birthday",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="state",column="state",javaType = String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="cert_type",column="cert_type",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="cert_no",column="cert_no",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="photograph",column="photograph",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="nation",column="nation",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="politics",column="politics",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="edu_type",column="edu_type",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="edu_hierarchy",column="edu_hierarchy",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="subject_category",column="subject_category",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="degree_type",column="degree_type",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="academy_name",column="academy_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="specialty_name",column="specialty_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="job",column="job",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="workunit",column="workunit",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="address",column="address",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="postal_code",column="postal_code",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="mobile_phone",column="mobile_phone",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="tel_phone",column="tel_phone",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="email",column="email",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="resume",column="resume",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="origin_recommand",column="origin_recommand",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		//@Result(property="prize_id",column="prize_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		//@Result(property="elect_join_id",column="elect_join_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="attachment",column="attachment",javaType=String.class,jdbcType=JdbcType.VARCHAR)
		
	})
	
	@Insert({"insert into candidate_table(name, sex, birthday, state, cert_type, cert_no, photograph, nation, " + 
				"politics, edu_type, edu_hierarchy, subject_category, degree_type, academy_name, specialty_name, " + 
				"job, title, workunit, address, postal_code, mobile_phone, tel_phone, email, resume, origin_recommand, " +
				"attachment" ,
			"values(#{name}, #{sex}, #{birthday}, #{state}, #{cert_type}, #{cert_no}, #{photograph}, #{nation}, " +
				"#{politics}, #{edu_type}, #{edu_hierarchy}, #{subject_category}, #{degree_type}, #{academy_name}, #{specialty_name}, " +
				"#{job}, #{title}, #{workunit}, #{address}, #{postal_code}, #{mobile_phone}, #{tel_phone}, #{email}, #{resume}, #{origin_recommand}, " +
				"#{attachment}"})
	public boolean addCandidate(
			@Param("name") String name,
			@Param("sex") int sex,
			@Param("birthday") String birthday,
			@Param("state") String state,
			@Param("cert_type") String cert_type,
			@Param("cert_no") String cert_no,
			@Param("photograph") String photograph,
			@Param("nation") String nation,
			@Param("politics") String politics,
			@Param("edu_type") String edu_type,
			@Param("edu_hierarchy") String edu_hierarchy,
			@Param("subject_category") String subject_category,
			@Param("degree_type") String degree_type,
			@Param("academy_name") String academy_name,
			@Param("specialty_name") String specialty_name,
			@Param("job") String job,
			@Param("title") String title,
			@Param("workunit") String workunit,
			@Param("address") String address,
			@Param("postal_code") String postal_code,
			@Param("mobile_phone") String mobile_phone,
			@Param("tel_phone") String tel_phone,
			@Param("email") String email,
			@Param("resume") String resume,
			@Param("origin_recommand") String origin_recommand
		);
	
	@Select("select * from candidate_table where academy_name=#{academy_name};")
	public List<Candidate> getCandidatesOfSchool(@Param("academy_name") String academy_name);
	
	@Select("select * from candidate_table where name=#{name};")
	public List<Candidate> getCandidateByName(@Param("name") String name);
	
	
	/*
	 * 查询候选导师的摘要信息
	 */
	@Results({
		@Result(property="id",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="name",column="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="workunit",column="workunit",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="photograph",column="photograph",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="job",column="job",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="attachment",column="attachment",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Select({"select id,`name`,workunit,photograph,job,title,`attachment` "
			+ "from  cydspx.candidate_table as b left join "
			+ "( select candidate_id from cydspx.candidate_group_table where group_id=#{groupId}) as a on b.id= a.candidate_id;"})
	public List<CandidateAbstract> getCanidateAbstractByGroupId(@Param("groupId") int groupId);
	
	
	
}
