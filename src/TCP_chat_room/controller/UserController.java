package TCP_chat_room.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import TCP_chat_room.model.ClientInfo;
import TCP_chat_room.model.Const;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.server.ServerManager;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.model.protocol.Request;
import TCP_chat_room.service.UserService;

/**
 * 	UserController  �����û��������ӿ�
 * @author Shinelon
 *
 */
public class UserController {
	
	/*��½�ӿ�*/
	public static Response login(Request request) {
		//У�����
		JSONObject parseObject = JSONObject.parseObject(request.getBody());
		String username = parseObject.getString("username");
		String password = parseObject.getString("password");
		int udpPort = parseObject.getInteger("udpPort");
		if(request.getRequestType()!=Const.RequestTypeEnum.LOGIN) {
			return Response.createByErrorMessage("�������ʹ���");
		}
		if((username==null||username.length()==0)||password==null||password.length()==0) {
			return Response.createByErrorMessage("�û���������Ϊ��");
		}

		//װ������
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		//��װclientInfo
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setUser(user);
		clientInfo.setClientIp(request.getSrcIp());
		clientInfo.setCookie(new Cookie(user.getUid(),request.getCookie()));
		clientInfo.setTCP_port(request.getSrcPort_tcp());
		clientInfo.setUDP_port(udpPort);
		
		return UserService.login(user,clientInfo);	
	}
	
	/*�˳��ӿ�*/
	public static Response logout(Request request) {
		//װ������
		String clientInfoStr = request.getSrcIp()+":"+request.getSrcPort_tcp();
		boolean userLogout = ServerManager.getSM().userLogout(clientInfoStr);
		if(userLogout) {
			return Response.createBySuccess("�˳��ɹ�",null);
		}else {
			return Response.createBySuccess("���û����Ӳ�����",null);
		}
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
