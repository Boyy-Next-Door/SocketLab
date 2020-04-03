package TCP_chat_room.test;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import TCP_chat_room.dao.UserDao;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.server.ServerManager;
import TCP_chat_room.utils.MyCookieUtil;
public class Test1 {
	@Test
//	public static Response login(User user) {
		public  void login() {
		User user = new User();
		user.setUsername("u123");
		user.setPassword("123");
		UserDao userDao = new UserDao();
		try {
//			boolean isVerifySuccess = userDao.verifyUser(user.getUsername(), user.getPassword());
//			//账密校验通过
//			if(isVerifySuccess) {
//				System.out.println("success");
//				//设置cookie 会将已有的cookie覆盖 相当于顶别人的号
////				Cookie generateCookie = MyCookieUtil.generateCookie(user.getUid());
////				ServerManager.sm.setCookie(generateCookie);
//				//cookie持久化到数据库
//				
//			}
			Cookie cookie = new Cookie();
			cookie.setUid(2);
			cookie.setCookie("aaaaaaa");
			cookie.setCreate_at(new Date());
			userDao.insertCookie(cookie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
