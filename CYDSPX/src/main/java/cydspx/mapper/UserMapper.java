package cydspx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import cydspx.mode.User;

public interface UserMapper {
	@Results({
		@Result(property="userId",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="userName",column="user_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="password",column="password",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="userType",column="user_type",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="email",column="email",javaType=String.class,jdbcType=JdbcType.VARCHAR)
		
	})
	@Select("SELECT * FROM user_table where user_name=#{userName};")
	public User getUserByUserName(@Param("userName") String userName);
	
	
	@Insert("INSERT INTO `cydspx`.`user_table` (`user_name`,  `password`, `user_type`) VALUES (#{username}, #{password}, #{type});")
	public void insertUser(
			@Param("username") String username,
			@Param("password") String password,
			@Param("type") int type
			);
	
	
	
	@Results({
		@Result(property="userId",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="userName",column="user_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="password",column="password",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="userType",column="user_type",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="email",column="email",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Select("SELECT * FROM cydspx.user_table where user_type=#{type};")
	public List<User> getAllUsersByType(@Param("type") int type);
	
	
	
	
	
	
	@Results({
		@Result(property="userId",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="userName",column="user_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="password",column="password",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="userType",column="user_type",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="email",column="email",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Select("SELECT * FROM user_table")
	public List<User> getAllUserList();
	
	@Delete("DELETE FROM `cydspx`.`user_table` WHERE id=#{userId};")
	public void deleteUser(
			@Param("userId") int userId
			);
	
	 
	@Update("UPDATE `cydspx`.`user_table` SET `password`=#{password} WHERE `id`=#{userId};")
	public void setUserPassword(
			@Param("userId") int userId,
			@Param("password") String password
			);
	
	
}










