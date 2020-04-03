package TCP_chat_room.server;

import java.util.HashMap;
import java.util.Map;

import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;

/**
 * 服务器状态中心
 * 
 * @author GeorgeYang
 *
 */
public class ServerManager {
	private ServerManager() {
		// 初始化后台数据
		onlineUserNum = 0;
		onlineUserMap = new HashMap<>();
		cookies = new HashMap<>();
	}

	/* 全局单例 */
	private static ServerManager sm;

	public static ServerManager getSM() {
		if(sm==null) {
			sm=new ServerManager();
		}
		return sm;	
	}
	
	/* 服务器状态数据 */
	private int onlineUserNum; // 在线用户
	private Map<Long, User> onlineUserMap; // <id,user> 集合
	private Map<Long, Cookie> cookies; // <id,cookie>集合 用于会话状态校验

//	/* 静态代码块初始化 */
//	{
//		// 创建单例对象
//		sm = new ServerManager();
//	}

	// 用户登录
	public synchronized void userLogin(User user, Cookie ck) {
		onlineUserMap.put(user.getUid(), user);
		cookies.put(user.getUid(), ck);
		onlineUserNum++;
		// TODO
	}

	// 用户退出
	public synchronized void userLogout(User user) {
		onlineUserMap.remove(user.getUid());
		cookies.remove(user.getUid());
		onlineUserNum--;
		// TODO
	}

	// 设置用户cookie 会覆盖旧的cookie
	public void setCookie(Cookie ck) {
		cookies.put(ck.getUid(), ck);
	}

	public int getOnlineUserNum() {
		return onlineUserNum;
	}

	public Map<Long, User> getOnlineUserMap() {
		return onlineUserMap;
	}

	private void setOnlineUserNum(int onlineUserNum) {
		this.onlineUserNum = onlineUserNum;
	}

	private void setOnlineUserMap(Map<Long, User> onlineUserMap) {
		this.onlineUserMap = onlineUserMap;
	}

}
