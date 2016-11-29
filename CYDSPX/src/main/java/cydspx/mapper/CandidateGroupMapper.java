package cydspx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import cydspx.mode.CandidateAbstract;

public interface CandidateGroupMapper {

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
	@Select("SELECT id,`name`,workunit,photograph,job,title,`attachment` FROM candidate_table as a right join  ( select candidate_id  from cydspx.candidate_group_table where group_id=#{groupId}) as b on a.id = b.candidate_id;")
	List<CandidateAbstract> getAllCandidateBygroupId(@Param("groupId") int groupId);
	
	@Select("SELECT count(id) FROM cydspx.candidate_group_table where group_id=#{groupId} and candidate_id=#{candidateId};")
	int containCandidateOfGroup(@Param("groupId") int groupId,@Param("candidateId") int candidateId);
	
	@Insert("INSERT INTO `cydspx`.`candidate_group_table` (`group_id`, `candidate_id`) VALUES (#{groupId}, #{candidateId});")
	int insertCandidateGroupInfo(@Param("groupId") int groupId,@Param("candidateId") int candidateId);
	
	@Delete(" TRUNCATE table cydspx.candidate_group_table;")
	void truncateTable();
}
