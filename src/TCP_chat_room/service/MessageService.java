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

/*用户模块业务逻辑*/
public class MessageService {

	/* 发送消群聊息接口 */
	public static Response sendToGroupChatRoom(Message message) {
		UserDao userDao = new UserDao();
		try {
			//消息入库 当且仅当接受方和发送方id均存在时才插入记录
			MessageDao messageDao = new MessageDao();
			boolean success = messageDao.insertGroupMessage(message);
			if(!success) {
				return Response.createByErrorMessage("消息参数错误");
			}
			
			//入库成功 需要告知messageDispatcher 创建一个新的推送任务
			MessageDispatchService.sendMSGtoClient(message);
			
			//消息发送成功
			return Response.createBySuccess("发送成功", null);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return Response.createByErrorMessage("服务器异常");
		}
	}

}
