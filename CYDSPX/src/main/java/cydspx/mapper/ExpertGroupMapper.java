package cydspx.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ExpertGroupMapper {
	
	/*
	 * 根据用户ID获取专家所在组的ID
	 */
	@Select("SELECT group_id FROM expert_group_table where user_id=#{userID};")
	public Integer getGroupIdByUserID(@Param("userID") int userID);
	
	/*
	 * 保存专家分组信息
	 */
	@Insert("INSERT INTO `cydspx`.`expert_group_table` (`group_id`, `user_id`) VALUES (#{groupId}, #{userId});")
	public int saveExpertGroupInfo(@Param("groupId") int groupId,@Param("userId") int userId);
	
	/*
	 * 清空表数据
	 */
	@Delete("truncate table `cydspx`.`expert_group_table`;")
	void truncateTable();
}
