package cydspx.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ExpertGroupMapper {
	
	/*
	 * 根据用户ID获取专家所在组的ID
	 */
	@Select("SELECT group_id FROM expert_group_table where user_id=#{userID};")
	public int getGroupIdByUserID(@Param("userID") int userID);
}
