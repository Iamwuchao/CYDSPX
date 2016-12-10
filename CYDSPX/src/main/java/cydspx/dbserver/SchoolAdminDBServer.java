package cydspx.dbserver;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cydspx.mapper.UserMapper;
import cydspx.globalInfo.ResponseCode;
import cydspx.mode.ResponseMessage;


@Data
@Service
public class SchoolAdminDBServer {
	
	@Autowired
	private UserMapper userDBMapper;
	
	public ResponseMessage saveSummarize(int userId, String fileDir){
		ResponseMessage responseMessage = new ResponseMessage();
		if(userDBMapper.updateSummarizeByUserId(fileDir, userId)){
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal());
		}
		else {
			responseMessage.setCode(ResponseCode.FAIL.ordinal());
		}
		return responseMessage;
	}
}
