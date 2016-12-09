package cydspx.mapper.relation;

import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CandidateServiceRelationMapper {
	
	@Insert("INSERT INTO `candidate_service_relation_table` (`candidate_id`,  `service`) VALUES (#{candidate_id}, #{service});")
	public int addServiceItem(
			@Param("candidate_id") int candidate_id,
			@Param("service") String service
			);

	@Delete("DELETE FROM `candidate_service_relation_table` where candidate_id=#{candidateId}")
	public int deleteCandidateServiceRelation(@Param("candidateId") int candidateId);

	@Select("select service from candidate_service_relation_table where candidate_id=#{candidate_id};")
	public List<String> getElectJoin(@Param("candidate_id") int candidate_id);

}
