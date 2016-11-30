package cydspx.mode;

import lombok.Data;

@Data
public class ResponseMessage {
	private int code;//表示状态
	private String message;//消息
	private String href;
}
