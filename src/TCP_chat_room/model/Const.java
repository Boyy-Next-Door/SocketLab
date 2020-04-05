package TCP_chat_room.model;

import java.net.Socket;

/**
 * 常量字段
 * 
 * @author Shinelon
 *
 */
public class Const {
	//服务器ip
	public static String SERVER_IP = "192.168.1.3";
	//服务器端程序端口
	public static int SERVER_PORT = 23333;
	//默认群号
	public static int ROOT_ROOM_ID = 1;
	//请求类型枚举 （暂时用这个来定义功能接口 之后要重构这部分设计）
	public interface RequestTypeEnum {
		public static int LOGIN = 401;
		public static int REGISTER = 402;
		public static int SENDMSG = 403;
		public static int LOGOUT = 404;
		public static int ONLINE_NUMBER = 405;
	}
	
	//用户类型枚举
	public interface UserTypeEnum{
		public static int NORMAL_USER =101;
		public static int ADMINISTRATOR =102;
	}
	
	//消息类型枚举
	public interface MessageTypeEnum{
		public static int TEXT=301;
		public static int IMAGE=302;
		public static int FILE=303;
	}
	
	//消息发送频道枚举
	public interface MessageChannelTypeTypeEnum{
		public static int PRIVATE_CHAT=311;				//私聊
		public static int GROUP_CHAT=312;				//群聊
		public static int SYSTEM_NOTIFICATION_ONLINE_NUMBER=313;		//系统通知 -推送在线人数
	}
}
