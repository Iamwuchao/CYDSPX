package cydspx.mapper.relation;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CandidateVocationRelationMapper {
	
	@Insert("INSERT INTO `candidate_vocation_relation_table` (`candidate_id`,  `vocation`) VALUES (#{candidate_id}, #{vocation});")
	public int addVocationItem(
			@Param("password") int candidate_id,
			@Param("type") String vocation
			);
	
	@Delete("delete from `candidate_vocation_relation_table` where candidate_id = #{candidateId}")
	public int deleteVocationItem(@Param("candidateId") int candidateId);
}
