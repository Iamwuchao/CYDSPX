package cydspx.mapper.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CandidateServiceRelationMapper {
	
	@Insert("INSERT INTO `cydspx`.`candidate_service_relation_table` (`candidate_id`,  `service`) VALUES (#{candidate_id}, #{service});")
	public boolean addServiceItem(
			@Param("password") int candidate_id,
			@Param("type") String service
			);
}
