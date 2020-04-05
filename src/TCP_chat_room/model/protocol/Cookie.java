package TCP_chat_room.model.protocol;

import java.util.Date;

/*用于记录客户端、服务器端会话状态*/
public class Cookie {
	private long uid;
	private String cookie;		//采用MD5(MD5(currentTimeMillis) + uid）的方式生成唯一cookie 
	private boolean expired;	//过期标志
	private Date create_at;		//创建时间
	public  Cookie(){}	
	public Cookie(long uid, String cookie) {
		this.uid=uid;
		this.cookie=cookie;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	
}
