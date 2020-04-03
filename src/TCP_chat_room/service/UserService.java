package TCP_chat_room.service;

import java.sql.SQLException;

import TCP_chat_room.dao.UserDao;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.server.ServerManager;
import TCP_chat_room.utils.MyCookieUtil;

/*用户模块业务逻辑*/
public class UserService {
	public static Response login(User user) {
		UserDao userDao = new UserDao();
		try {
			boolean isVerifySuccess = userDao.verifyUser(user.getUsername(), user.getPassword());
			//账密校验通过
			if(isVerifySuccess) {
				//设置cookie 会将已有的cookie覆盖 相当于顶别人的号
				Cookie generateCookie = MyCookieUtil.generateCookie(user.getUid());
				ServerManager.getSM().setCookie(generateCookie);
				//cookie持久化到数据库
				userDao.insertCookie(generateCookie);
				//response中回传cookie串
				return Response.createBySuccess("登陆成功", generateCookie.getCookie());
			}
			//账密校验未通过
			else {
				return Response.createByErrorMessage("账号或密码错误");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.createByErrorMessage("服务器异常");
		}
	}
	
	public static Response register(User user) {
		UserDao userDao = new UserDao();
		try {
			boolean isUsernameExist = userDao.isUsernameExist(user.getUsername());
			//账号未注册
			if(!isUsernameExist) {
				boolean success = userDao.insertUser(user);
				if(success) {
					return Response.createBySuccess("注册成功",null);					
				}else {
					return Response.createByErrorMessage("服务器异常");
				}
			}
			//用户名已存在
			else {
				return Response.createByErrorCode(Response.ERROR_CODE_USER_ALREADY_EXIST);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.createByErrorMessage("服务器异常");
		}
	}
}
