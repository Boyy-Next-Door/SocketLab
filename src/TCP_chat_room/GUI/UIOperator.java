package TCP_chat_room.GUI;

import TCP_chat_room.model.db.Message;

/* ui��������ӿ� �ɾ����UI�߳�ʵ�� ע���TCPclient ���ṩclient��UI����Ĳ�������*/
public interface UIOperator {
	//��ʾһ����Ϣ
	public void showMessage(Message message);
}
