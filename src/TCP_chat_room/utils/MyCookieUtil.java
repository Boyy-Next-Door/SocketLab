package TCP_chat_room.utils;


import java.util.Date;

import TCP_chat_room.model.protocol.Cookie;

/*�Զ���ļ�cookie���ɹ�����*/
public class MyCookieUtil {
	//����MD5(MD5(currentTimeMillis) + uid���ķ�ʽ����Ψһcookie 
	public static Cookie generateCookie(long uid) {
		Cookie cookie = new Cookie();
		String md5Encode1 = MD5Util.MD5Encode(String.valueOf(System.currentTimeMillis()), "utf8");
		String md5Encode2 = MD5Util.MD5Encode(md5Encode1+String.valueOf(uid), "utf8");
		cookie.setCookie(md5Encode2);
		cookie.setExpired(false);
		cookie.setCreate_at(new Date());
		return cookie;
	}
}
