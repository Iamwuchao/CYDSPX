package cydspx.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.type.JdbcType;

public interface ElectJoinMapper {
	@Results({
		@Result(property="candidate_id",column="candidate_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="project_name",column="project_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="prize_year",column="prize_year",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="level",column="level",javaType=String.class,jdbcType=JdbcType.VARCHAR)
		
	})
	@Insert("INSERT INTO  `elect_join_table` (`candidate_id`,  `project_name`, `elect_year`, `level`) VALUES (#{candidate_id}, #{project_name}, #{elect_year}, #{level});")
	public int addElectJoinItem(
			@Param("candidate_id") int candidate_id,
			@Param("project_name") String project_name,
			@Param("elect_year") String elect_year,
			@Param("level") String level
			);
	
	@Delete("DELETE FROM `elect_join_table` where candidate_id=#{candidateId}")
	public int removeElectJoinItem(@Param("candidateId") int candidateId);
	
	@Delete("DELETE FROM `elect_join_table` where candidate_id=#{candidateId} and id=#{id}")
	public int removeOneElectJoinItem(@Param("candidateId") int candidateId,@Param("id") int id);
	
	
	
}
