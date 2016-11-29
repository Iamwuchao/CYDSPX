package cydspx.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/*
 * 专家组 和 候选人 组织间的对应关系
 */
public interface AllocateMapper {
	
	/*
	 * 获取分配给专家组的候选人组的ID
	 */
	@Select("SELECT candidate_group_id FROM cydspx.allocate_table where expert_group_id=#{expertGroupId};")
	public int getCandidateGroupId(@Param("expertGroupId") int expertGroupId);
	
	@Insert("INSERT INTO `cydspx`.`allocate_table` (`candidate_group_id`, `expert_group_id`) VALUES (#{candidateGroupId}, #{expertGroupId});")
	public int saveGroupInfo(@Param("candidateGroupId") int candidateGroupId,@Param("expertGroupId") int expertGroupId);
	
	@Delete("TRUNCATE TABLE `cydspx`.`allocate_table`;")
	void truncateTable();
}
