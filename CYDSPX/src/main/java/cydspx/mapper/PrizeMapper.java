package cydspx.mapper;


import org.apache.ibatis.annotations.Delete;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import cydspx.mode.ElectJoin;
import cydspx.mode.Prize;

public interface PrizeMapper {
	@Results({
		@Result(property="candidate_id",column="candidate_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="achievement",column="achievement",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="prize_year",column="prize_year",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="level",column="level",javaType=String.class,jdbcType=JdbcType.VARCHAR)
		
	})
	
	
	@Insert("INSERT INTO  `prize_table` (`candidate_id`,  `achievement`, `prize_year`, `level`) VALUES (#{candidate_id}, #{achievement}, #{prize_year}, #{level});")
	public int addPrizeItem(
			@Param("candidate_id") int candidate_id,
			@Param("achievement") String achievement,
			@Param("prize_year") String prize_year,
			@Param("level") String level
			);
	

	@Delete("DELETE FROM  `prize_table` where candidate_id=#{candidateId}")
	public int removePrize(@Param("candidateId") int candidateId);
	
	@Delete("DELETE FROM  `prize_table` where candidate_id=#{candidateId} and id=#{id}")
	public int removeOnePrize(@Param("candidateId") int candidateId,@Param("id") int id);

	@Select("select id from prize_table where candidate_id=#{candidate_id};")
	public List<Integer> getPrizeIds(@Param("candidate_id") int candidate_id);
	
	@Results({
		@Result(property="candidate_id",column="candidate_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="achievement",column="achievement",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="prize_year",column="prize_year",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="prize_level",column="level",javaType=String.class,jdbcType=JdbcType.VARCHAR)
		
	})
	@Select("select * from prize_table where id=#{prize_id};")
	public Prize getPrizeById(@Param("prize_id") int prize_id);
	
	/*
	@Select("select * from prize_table where id in #{prize_ids};")
	public List<Prize> getPrizesByIds(@Param("prize_ids") List<Integer> prize_ids);
	*/

}
