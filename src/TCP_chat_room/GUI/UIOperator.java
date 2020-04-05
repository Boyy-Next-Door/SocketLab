package TCP_chat_room.GUI;

import TCP_chat_room.model.db.Message;

/* ui操作代理接口 由具体的UI线程实现 注入给TCPclient 以提供client对UI组件的操作功能*/
public interface UIOperator {
	//显示一条消息
	public void showMessage(Message message);
}
