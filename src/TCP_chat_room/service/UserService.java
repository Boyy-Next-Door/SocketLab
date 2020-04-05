package TCP_chat_room.service;

import java.sql.SQLException;

import TCP_chat_room.dao.UserDao;
import TCP_chat_room.model.ClientInfo;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.server.ServerManager;
import TCP_chat_room.utils.MyCookieUtil;

/*�û�ģ��ҵ���߼�*/
public class UserService {
	public static Response login(User user,ClientInfo clientInfo) {
		UserDao userDao = new UserDao();
		try {
			boolean isVerifySuccess = userDao.verifyUser(user.getUsername(), user.getPassword());
			//����У��ͨ��
			if(isVerifySuccess) {
				//����cookie �Ὣ���е�cookie���� �൱�ڶ����˵ĺ� ���µ�SM�е�ClientInfo
				Cookie generateCookie = MyCookieUtil.generateCookie(user.getUid());
				//����clientInfo��cookie����
				clientInfo.setCookie(generateCookie);
				//����clientInfo��user���� ���ﴫ���user�������˺�����
				clientInfo.setUser(userDao.selectUserByUsername(user.getUsername()));
				//�Ѹղ鵽��user��id���������
				clientInfo.setUid(clientInfo.getUser().getUid());
				//����clientInfo��userInfo����
				clientInfo.setUserInfo(userDao.selectUserInfoByUsername(user.getUsername()));
				//�������������clientInfo �����޸ĵ�ǰ��������
				ServerManager.getSM().userLogin(clientInfo);
				//TODO  �����cookieʵ���ϲ���Ҫ�־û�  ��Ϊ�����ǿͻ��� ������web�Ϲر��������Ȼά�ֻỰ״̬�����
				// ����cookie�����Ӧ���պ���˵ Ŀǰcookie��������Ϊ��½֮����������ӿ�ʱ����ݱ�ʶ
				//cookie�־û������ݿ�
				userDao.insertCookie(generateCookie);
				//response�лش�clientInfo
				System.out.println(clientInfo);
				return Response.createBySuccess("��½�ɹ�", clientInfo);
			}
			//����У��δͨ��
			else {
				return Response.createByErrorMessage("�˺Ż��������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.createByErrorMessage("�������쳣");
		}
	}
	
	public static Response register(User user) {
		UserDao userDao = new UserDao();
		try {
			boolean isUsernameExist = userDao.isUsernameExist(user.getUsername());
			//�˺�δע��
			if(!isUsernameExist) {
				boolean success = userDao.insertUser(user);
				if(success) {
					return Response.createBySuccess("ע��ɹ�",null);					
				}else {
					return Response.createByErrorMessage("�������쳣");
				}
			}
			//�û����Ѵ���
			else {
				return Response.createByErrorCode(Response.ERROR_CODE_USER_ALREADY_EXIST);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.createByErrorMessage("�������쳣");
		}
	}
}
