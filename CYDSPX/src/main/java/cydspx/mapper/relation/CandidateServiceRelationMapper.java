package cydspx.mapper.relation;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CandidateServiceRelationMapper {
	
	@Insert("INSERT INTO `cydspx`.`candidate_service_relation_table` (`candidate_id`,  `service`) VALUES (#{candidate_id}, #{service});")
	public int addServiceItem(
			@Param("candidate_id") int candidate_id,
			@Param("service") String service
			);
	
	@Delete("DELETE FROM `candidate_service_relation_table` where candidate_id=#{candidateId}")
	public int deleteCandidateServiceRelation(@Param("candidateId") int candidateId);
}
