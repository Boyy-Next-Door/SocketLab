package TCP_chat_room.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import TCP_chat_room.model.ClientInfo;
import TCP_chat_room.model.db.Message;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Request;
import TCP_chat_room.server.ServerManager;

/*��Ϣ���ͷ���*/
public class MessageDispatchService {
	/**
	 * ��ͻ��˻�д���� ����UDPЭ�� ���е������ݴ��� �ڿͻ��˹̶��˿�33333����UDP���ݰ�
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * 
	 */
	public static void sendMSGtoClient(Message message) throws UnknownHostException, IOException {
		// �������Ͷ�Socket����
		DatagramSocket ds = new DatagramSocket();
		// ��װ��������
		// ��ȡ���ܿͻ��б�
		Map<Long, ClientInfo> clientInfoMap = ServerManager.getSM().getClientInfoMap();
		for (Entry<Long, ClientInfo> entry : clientInfoMap.entrySet()) {
			// ��ȡ���Ŀͻ�����Ϣ
			ClientInfo clientInfo = entry.getValue();
			// �������ݲ����
			byte[] bys = JSON.toJSONString(message).getBytes();
			DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName(clientInfo.getClientIp()),
					clientInfo.getUDP_port());
			// ��������
			ds.send(dp);

		}
//		message.get
		// �������ݲ����
//		byte[] bys = .getBytes();
//		DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("192.168.1.3"), 23333);	
//			//��������
//			ds.send(dp);
//		}
	}

}
