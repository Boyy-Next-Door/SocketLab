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

	public interface RequestTypeEnum {
		public static int LOGIN = 401;
		public static int REGISTER = 402;
		public static int SENDMSG = 403;
		public static int LOGOUT = 404;
	}
	
	public interface UserTypeEnum{
		public static int NORMAL_USER =101;
		public static int ADMINISTRATOR =102;
	}
}
