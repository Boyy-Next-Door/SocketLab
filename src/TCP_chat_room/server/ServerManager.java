package TCP_chat_room.server;

import java.util.HashMap;
import java.util.Map;

import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;

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
		onlineUserMap = new HashMap<>();
		cookies = new HashMap<>();
	}

	/* ȫ�ֵ��� */
	private static ServerManager sm;

	public static ServerManager getSM() {
		if(sm==null) {
			sm=new ServerManager();
		}
		return sm;	
	}
	
	/* ������״̬���� */
	private int onlineUserNum; // �����û�
	private Map<Long, User> onlineUserMap; // <id,user> ����
	private Map<Long, Cookie> cookies; // <id,cookie>���� ���ڻỰ״̬У��

//	/* ��̬������ʼ�� */
//	{
//		// ������������
//		sm = new ServerManager();
//	}

	// �û���¼
	public synchronized void userLogin(User user, Cookie ck) {
		onlineUserMap.put(user.getUid(), user);
		cookies.put(user.getUid(), ck);
		onlineUserNum++;
		// TODO
	}

	// �û��˳�
	public synchronized void userLogout(User user) {
		onlineUserMap.remove(user.getUid());
		cookies.remove(user.getUid());
		onlineUserNum--;
		// TODO
	}

	// �����û�cookie �Ḳ�Ǿɵ�cookie
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
