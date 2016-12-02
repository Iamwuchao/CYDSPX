package cydspx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	
	@Insert("insert into candidate_table(name, sex, birthday, state, cert_type, cert_no, photograph, nation, " + 
				"politics, edu_type, edu_hierarchy, subject_category, degree_type, academy_name, specialty_name, " + 
				"job, title, workunit, address, postal_code, mobile_phone, tel_phone, email, resume, origin_recommand, attachment) " + 
			"values(#{candidate.name}, #{candidate.sex}, #{candidate.birthday}, #{candidate.state}, #{candidate.cert_type}, #{candidate.cert_no}, #{candidate.photograph}, #{candidate.nation}, " +
				"#{candidate.politics}, #{candidate.edu_type}, #{candidate.edu_hierarchy}, #{candidate.subject_category}, #{candidate.degree_type}, #{candidate.academy_name}, #{candidate.specialty_name}, " +
				"#{candidate.job}, #{candidate.title}, #{candidate.workunit}, #{candidate.address}, #{candidate.postal_code}, #{candidate.mobile_phone}, #{candidate.tel_phone}, #{candidate.email}, #{candidate.resume}, #{candidate.origin_recommand}, #{candidate.attachment});")
	@Options(useGeneratedKeys=true, keyProperty="candidate.id",keyColumn="id")
	public int addCandidate(@Param("candidate") Candidate candidate);
	
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
	
	
	/*
	 * 查询全部候选导师的摘要信息
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
	@Select("SELECT id,`name`,workunit,photograph,job,title,`attachment`  FROM cydspx.candidate_table;")
	public List<CandidateAbstract> getAllCateAbstract();
	
	
	/*
	 * 查询全部候选导师的摘要信息
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
	@Select("SELECT id,`name`,workunit,photograph,job,title,`attachment`  FROM cydspx.candidate_table order by score limit #{number};")
	public List<CandidateAbstract> getCateAbstractList(@Param("number") int number);
	
	
	@Select("SELECT id FROM cydspx.candidate_table;")
	public List<Integer> getAllCandidateId();
	
	@Update("UPDATE `cydspx`.`candidate_table` SET `score`=#{score} WHERE `id`=#{candidateId};")
	public int updateCandidateScore(@Param("score") double score,@Param("candidateId") int candidateId);
	
	
}
