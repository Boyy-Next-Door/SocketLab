package TCP_chat_room.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import TCP_chat_room.model.ClientInfo;
import TCP_chat_room.model.Const;
import TCP_chat_room.model.db.Message;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.service.MessageDispatchService;
import TCP_chat_room.service.MessageService;

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
		clientInfoMap = new HashMap<>();
	}

	/* 全局单例 */
	private static ServerManager sm;

	public static ServerManager getSM() {
		if (sm == null) {
			sm = new ServerManager();
		}
		return sm;
	}

	/* 服务器状态数据 */
	private int onlineUserNum; // 在线用户
	private Map<Long, ClientInfo> clientInfoMap; // <id,cookie>集合 用于会话状态校验

//	/* 静态代码块初始化 */
//	{
//		// 创建单例对象
//		sm = new ServerManager();
//	}

	// 用户登录
	public synchronized void userLogin(ClientInfo clientInfo) {
		clientInfoMap.put(clientInfo.getUid(),clientInfo);
		modifyOnlineUserNum(1);		//加1
	}

	// 用户退出		由于创建socket的时候只能确定客户端的ip和Port 可以通过这两个元素唯一确定这个用户
	public synchronized boolean userLogout(String clientInfo) {
		
		for (Entry<Long, ClientInfo> entry : clientInfoMap.entrySet()) {
			String clientInfoStr = entry.getValue().getClientIp()+":"+entry.getValue().getTCP_port();
			//是这个客户端的clientInfo
			if(clientInfoStr.equals(clientInfo)) {
				//删除它
				clientInfoMap.remove(entry.getKey());
				modifyOnlineUserNum(-1);	//减1
				return true;
			}
		}
		return false;
	}

	public synchronized void modifyOnlineUserNum(int diff) {
		onlineUserNum+=diff;
		sendOnlineUserNumToClients();
	}
	
	public void sendOnlineUserNumToClients() {
		//构造一条message 
		
		Message message = new Message();
		message.setChannel_type(Const.MessageChannelTypeTypeEnum.SYSTEM_NOTIFICATION_ONLINE_NUMBER);
		message.setContent(String.valueOf(onlineUserNum));
		//需要告知messageDispatcher 创建一个新的推送任务
		try {
			MessageDispatchService.sendMSGtoClient(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getOnlineUserNum() {
		return onlineUserNum;
	}


	private void setOnlineUserNum(int onlineUserNum) {
		this.onlineUserNum = onlineUserNum;
	}



	public Map<Long, ClientInfo> getClientInfoMap() {
		return clientInfoMap;
	}

	public void setClientInfoMap(Map<Long, ClientInfo> clientInfoMap) {
		this.clientInfoMap = clientInfoMap;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		//设置clientInfo  如果已经存在 说明已经登陆 这属于被挤下线的情况 
		clientInfoMap.put(clientInfo.getUid(), clientInfo);
	}

}
