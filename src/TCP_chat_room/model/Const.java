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
	//Ĭ��Ⱥ��
	public static int ROOT_ROOM_ID = 1;
	//��������ö�� ����ʱ����������幦�ܽӿ� ֮��Ҫ�ع��ⲿ����ƣ�
	public interface RequestTypeEnum {
		public static int LOGIN = 401;
		public static int REGISTER = 402;
		public static int SENDMSG = 403;
		public static int LOGOUT = 404;
		public static int ONLINE_NUMBER = 405;
	}
	
	//�û�����ö��
	public interface UserTypeEnum{
		public static int NORMAL_USER =101;
		public static int ADMINISTRATOR =102;
	}
	
	//��Ϣ����ö��
	public interface MessageTypeEnum{
		public static int TEXT=301;
		public static int IMAGE=302;
		public static int FILE=303;
	}
	
	//��Ϣ����Ƶ��ö��
	public interface MessageChannelTypeTypeEnum{
		public static int PRIVATE_CHAT=311;				//˽��
		public static int GROUP_CHAT=312;				//Ⱥ��
		public static int SYSTEM_NOTIFICATION_ONLINE_NUMBER=313;		//ϵͳ֪ͨ -������������
	}
}
