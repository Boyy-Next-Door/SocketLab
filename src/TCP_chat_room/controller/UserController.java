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
 * 	UserController  处理用户相关请求接口
 * @author Shinelon
 *
 */
public class UserController {
	
	/*登陆接口*/
	public static Response login(Request request) {
		//校验参数
		JSONObject parseObject = JSONObject.parseObject(request.getBody());
		String username = parseObject.getString("username");
		String password = parseObject.getString("password");
		int udpPort = parseObject.getInteger("udpPort");
		if(request.getRequestType()!=Const.RequestTypeEnum.LOGIN) {
			return Response.createByErrorMessage("请求类型错误");
		}
		if((username==null||username.length()==0)||password==null||password.length()==0) {
			return Response.createByErrorMessage("用户名或密码为空");
		}

		//装载数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		//封装clientInfo
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setUser(user);
		clientInfo.setClientIp(request.getSrcIp());
		clientInfo.setCookie(new Cookie(user.getUid(),request.getCookie()));
		clientInfo.setTCP_port(request.getSrcPort_tcp());
		clientInfo.setUDP_port(udpPort);
		
		return UserService.login(user,clientInfo);	
	}
	
	/*退出接口*/
	public static Response logout(Request request) {
		//装载数据
		String clientInfoStr = request.getSrcIp()+":"+request.getSrcPort_tcp();
		boolean userLogout = ServerManager.getSM().userLogout(clientInfoStr);
		if(userLogout) {
			return Response.createBySuccess("退出成功",null);
		}else {
			return Response.createBySuccess("该用户连接不存在",null);
		}
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
