package cydspx.mapper.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CandidateVocationRelationMapper {
	
	@Insert("INSERT INTO `cydspx`.`candidate_vocation_relation_table` (`candidate_id`,  `vocation`) VALUES (#{candidate_id}, #{vocation});")
	public int addVocationItem(
			@Param("password") int candidate_id,
			@Param("type") String vocation
			);
	
	@Select("select * from candidate_vocation_relation_table where candidate_id=#{candidate_id};")
	public List<String> getElectJoin(@Param("candidate_id") int candidate_id);
}
