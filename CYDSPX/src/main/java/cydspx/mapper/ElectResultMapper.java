package cydspx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import cydspx.mode.CandidateAbstract;

public interface ElectResultMapper {
	/*
	 * 得到某位专家已评分信息用户的信息
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
	@Select("select id,`name`,workunit,photograph,job,title,`attachment` ,ret from candidate_table as a right join "
			+ "(SELECT candidate_id,ret FROM cydspx.elect_result_table where expert_id=#{expertId}) as b "
			+ "on a.id = b.candidate_id;")
	public List<CandidateAbstract> getGradedCandidateList(@Param("expertId") int expertId);
	
	@Insert("INSERT INTO `cydspx`.`elect_result_table` (`candidate_id`, `expert_id`, `ret`) VALUES (#{candidateId}, #{expertId}, #{score});")
	public int saveGradeInfo(@Param("candidateId") int candidateId,@Param("expertId") int expertId,@Param("score") int score);
	
	@Select("SELECT ret FROM cydspx.elect_result_table where candidate_id=#{candidateId} and expert_id=#{expertId};")
	public int getScore(@Param("candidateId")int candidateId,@Param("expertId") int expertId);
	
	@Select("select ifnull(sum(ret),0) from cydspx.elect_result_table where candidate_id=#{candidateId};")
	public int getSumScore(@Param("candidateId") int candidateId);
	
	@Select("select ifnull(count(id),1) from cydspx.elect_result_table where candidate_id=#{candidateId};")
	public int getCountJudgement(@Param("candidateId") int candidateId);
}
