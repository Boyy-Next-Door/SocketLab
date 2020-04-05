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
 * 	MessageController  处理消息发送请求接口
 * @author Shinelon
 *
 */
public class MessageController {
	
	/*发送接口*/
	public static Response sendMSG(Request request) {
		//校验参数
		Message message = JSON.parseObject(request.getBody(), Message.class);
		if(request.getRequestType()!=Const.RequestTypeEnum.SENDMSG) {
			return Response.createByErrorMessage("请求类型错误");
		}
		if(message==null||!message.checkMSG()) {
			return Response.createByErrorMessage("消息内容无效");
		}

		//根据不同频道 分发到不同service
		switch(message.getChannel_type()) {
			case Const.MessageChannelTypeTypeEnum.GROUP_CHAT:{
				Response sendToGroupChatRoom = MessageService.sendToGroupChatRoom(message);
				return sendToGroupChatRoom;
			}
		}
		
//		return UserService.login(user);	
		return null;
	}
	
	/*注册接口*/
	public static Response register(Request request) {
		//校验参数
		JSONObject parseObject = JSONObject.parseObject(request.getBody());
		String username = parseObject.getString("username");
		String password = parseObject.getString("password");
		if(request.getRequestType()!=Const.RequestTypeEnum.REGISTER) {
			return Response.createByErrorMessage("请求类型错误");
		}
		if((username==null||username.length()==0)||password==null||password.length()==0) {
			return Response.createByErrorMessage("用户名或密码为空");
		}

		//装载数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return UserService.register(user);	
	}
}
