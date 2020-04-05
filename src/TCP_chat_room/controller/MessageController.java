package TCP_chat_room.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import TCP_chat_room.model.Const;
import TCP_chat_room.model.db.Message;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Request;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.service.MessageService;
import TCP_chat_room.service.UserService;

/**
 * 	MessageController  ������Ϣ��������ӿ�
 * @author Shinelon
 *
 */
public class MessageController {
	
	/*���ͽӿ�*/
	public static Response sendMSG(Request request) {
		//У�����
		Message message = JSON.parseObject(request.getBody(), Message.class);
		if(request.getRequestType()!=Const.RequestTypeEnum.SENDMSG) {
			return Response.createByErrorMessage("�������ʹ���");
		}
		if(message==null||!message.checkMSG()) {
			return Response.createByErrorMessage("��Ϣ������Ч");
		}

		//���ݲ�ͬƵ�� �ַ�����ͬservice
		switch(message.getChannel_type()) {
			case Const.MessageChannelTypeTypeEnum.GROUP_CHAT:{
				Response sendToGroupChatRoom = MessageService.sendToGroupChatRoom(message);
				return sendToGroupChatRoom;
			}
		}
		
//		return UserService.login(user);	
		return null;
	}
	
	/*ע��ӿ�*/
	public static Response register(Request request) {
		//У�����
		JSONObject parseObject = JSONObject.parseObject(request.getBody());
		String username = parseObject.getString("username");
		String password = parseObject.getString("password");
		if(request.getRequestType()!=Const.RequestTypeEnum.REGISTER) {
			return Response.createByErrorMessage("�������ʹ���");
		}
		if((username==null||username.length()==0)||password==null||password.length()==0) {
			return Response.createByErrorMessage("�û���������Ϊ��");
		}

		//װ������
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return UserService.register(user);	
	}
}
