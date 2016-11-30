package cydspx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.type.JdbcType;

public interface PrizeMapper {
	@Results({
		@Result(property="candidate_id",column="candidate_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="achievement",column="achievement",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="prize_year",column="prize_year",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="level",column="level",javaType=String.class,jdbcType=JdbcType.VARCHAR)
		
	})
	
	
	@Insert("INSERT INTO `cydspx`.`prize_table` (`candidate_id`,  `achievement`, `prize_year`, `level`) VALUES (#{candidate_id}, #{achievement}, #{prize_year}, #{level});")
	public int addPrizeItem(
			@Param("candidate_id") int candidate_id,
			@Param("achievement") String achievement,
			@Param("prize_year") String prize_year,
			@Param("level") String level
			);
}
