package cydspx.mode;

import lombok.Data;

@Data
public class User {
	
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String school;
	private int userType;
}
