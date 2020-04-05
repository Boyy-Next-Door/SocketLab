package TCP_chat_room.service;

import java.io.IOException;
import java.sql.SQLException;

import TCP_chat_room.dao.MessageDao;
import TCP_chat_room.dao.UserDao;
import TCP_chat_room.model.db.Message;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.server.ServerManager;
import TCP_chat_room.utils.MyCookieUtil;

/*�û�ģ��ҵ���߼�*/
public class MessageService {

	/* ������Ⱥ��Ϣ�ӿ� */
	public static Response sendToGroupChatRoom(Message message) {
		UserDao userDao = new UserDao();
		try {
			//��Ϣ��� ���ҽ������ܷ��ͷ��ͷ�id������ʱ�Ų����¼
			MessageDao messageDao = new MessageDao();
			boolean success = messageDao.insertGroupMessage(message);
			if(!success) {
				return Response.createByErrorMessage("��Ϣ��������");
			}
			
			//���ɹ� ��Ҫ��֪messageDispatcher ����һ���µ���������
			MessageDispatchService.sendMSGtoClient(message);
			
			//��Ϣ���ͳɹ�
			return Response.createBySuccess("���ͳɹ�", null);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return Response.createByErrorMessage("�������쳣");
		}
	}

}
