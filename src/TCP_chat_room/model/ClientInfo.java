package TCP_chat_room.model;

import TCP_chat_room.model.db.User;
import TCP_chat_room.model.db.UserInfo;
import TCP_chat_room.model.protocol.Cookie;

//记录客户端的ip和端口
public class ClientInfo {
	private long uid;
	private User user;
	private UserInfo userInfo;
	private String clientIp;
	private int udp_port;
	private int tcp_port;
	private Cookie cookie;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Cookie getCookie() {
		return cookie;
	}

	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public int getUDP_port() {
		return udp_port;
	}

	public void setUDP_port(int uDP_port) {
		udp_port = uDP_port;
	}

	public int getTCP_port() {
		return tcp_port;
	}

	public void setTCP_port(int tCP_port) {
		tcp_port = tCP_port;
	}

	@Override
	public String toString() {
		return "ClientInfo [uid=" + uid + ", user=" + user + ", userInfo=" + userInfo + ", clientIp=" + clientIp
				+ ", UDP_port=" + udp_port + ", tcp_port=" + tcp_port + ", cookie=" + cookie + "]";
	}

}
