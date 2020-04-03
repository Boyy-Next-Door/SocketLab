package TCP_chat_room.model;

import java.net.Socket;

/**
 * �����ֶ�
 * 
 * @author Shinelon
 *
 */
public class Const {
	//������ip
	public static String SERVER_IP = "192.168.1.3";
	//�������˳���˿�
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
