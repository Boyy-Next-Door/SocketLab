package TCP_chat_room.model.protocol;

import java.util.Date;

/*���ڼ�¼�ͻ��ˡ��������˻Ự״̬*/
public class Cookie {
	private long uid;
	private String cookie;		//����MD5(MD5(currentTimeMillis) + uid���ķ�ʽ����Ψһcookie 
	private boolean expired;	//���ڱ�־
	private Date create_at;		//����ʱ��
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
