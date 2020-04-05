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
 * ������״̬����
 * 
 * @author GeorgeYang
 *
 */
public class ServerManager {
	private ServerManager() {
		// ��ʼ����̨����
		onlineUserNum = 0;
		clientInfoMap = new HashMap<>();
	}

	/* ȫ�ֵ��� */
	private static ServerManager sm;

	public static ServerManager getSM() {
		if (sm == null) {
			sm = new ServerManager();
		}
		return sm;
	}

	/* ������״̬���� */
	private int onlineUserNum; // �����û�
	private Map<Long, ClientInfo> clientInfoMap; // <id,cookie>���� ���ڻỰ״̬У��

//	/* ��̬������ʼ�� */
//	{
//		// ������������
//		sm = new ServerManager();
//	}

	// �û���¼
	public synchronized void userLogin(ClientInfo clientInfo) {
		clientInfoMap.put(clientInfo.getUid(),clientInfo);
		modifyOnlineUserNum(1);		//��1
	}

	// �û��˳�		���ڴ���socket��ʱ��ֻ��ȷ���ͻ��˵�ip��Port ����ͨ��������Ԫ��Ψһȷ������û�
	public synchronized boolean userLogout(String clientInfo) {
		
		for (Entry<Long, ClientInfo> entry : clientInfoMap.entrySet()) {
			String clientInfoStr = entry.getValue().getClientIp()+":"+entry.getValue().getTCP_port();
			//������ͻ��˵�clientInfo
			if(clientInfoStr.equals(clientInfo)) {
				//ɾ����
				clientInfoMap.remove(entry.getKey());
				modifyOnlineUserNum(-1);	//��1
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
		//����һ��message 
		
		Message message = new Message();
		message.setChannel_type(Const.MessageChannelTypeTypeEnum.SYSTEM_NOTIFICATION_ONLINE_NUMBER);
		message.setContent(String.valueOf(onlineUserNum));
		//��Ҫ��֪messageDispatcher ����һ���µ���������
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
		//����clientInfo  ����Ѿ����� ˵���Ѿ���½ �����ڱ������ߵ���� 
		clientInfoMap.put(clientInfo.getUid(), clientInfo);
	}

}
