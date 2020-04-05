package TCP_chat_room.service;

import java.sql.SQLException;

import TCP_chat_room.dao.UserDao;
import TCP_chat_room.model.ClientInfo;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.server.ServerManager;
import TCP_chat_room.utils.MyCookieUtil;

/*用户模块业务逻辑*/
public class UserService {
	public static Response login(User user,ClientInfo clientInfo) {
		UserDao userDao = new UserDao();
		try {
			boolean isVerifySuccess = userDao.verifyUser(user.getUsername(), user.getPassword());
			//账密校验通过
			if(isVerifySuccess) {
				//设置cookie 会将已有的cookie覆盖 相当于顶别人的号 更新到SM中的ClientInfo
				Cookie generateCookie = MyCookieUtil.generateCookie(user.getUid());
				//设置clientInfo的cookie属性
				clientInfo.setCookie(generateCookie);
				//设置clientInfo的user属性 这里传入的user仅仅有账号密码
				clientInfo.setUser(userDao.selectUserByUsername(user.getUsername()));
				//把刚查到的user的id单独提出来
				clientInfo.setUid(clientInfo.getUser().getUid());
				//设置clientInfo的userInfo属性
				clientInfo.setUserInfo(userDao.selectUserInfoByUsername(user.getUsername()));
				//这个方法会设置clientInfo 并且修改当前在线人数
				ServerManager.getSM().userLogin(clientInfo);
				//TODO  这里的cookie实际上不需要持久化  因为我们是客户端 不存在web上关闭浏览器依然维持会话状态的情况
				// 关于cookie更多的应用日后再说 目前cookie仅仅是作为登陆之后请求各个接口时的身份标识
				//cookie持久化到数据库
				userDao.insertCookie(generateCookie);
				//response中回传clientInfo
				System.out.println(clientInfo);
				return Response.createBySuccess("登陆成功", clientInfo);
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
