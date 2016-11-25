package cydspx.mapper;

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
}
