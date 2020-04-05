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

/*消息推送服务*/
public class MessageDispatchService {
	/**
	 * 向客户端回写数据 采用UDP协议 进行单向数据传输 在客户端固定端口33333接受UDP数据包
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * 
	 */
	public static void sendMSGtoClient(Message message) throws UnknownHostException, IOException {
		// 创建发送端Socket对象
		DatagramSocket ds = new DatagramSocket();
		// 封装发送数据
		// 获取接受客户列表
		Map<Long, ClientInfo> clientInfoMap = ServerManager.getSM().getClientInfoMap();
		for (Entry<Long, ClientInfo> entry : clientInfoMap.entrySet()) {
			// 获取他的客户端信息
			ClientInfo clientInfo = entry.getValue();
			// 创建数据并打包
			byte[] bys = JSON.toJSONString(message).getBytes();
			DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName(clientInfo.getClientIp()),
					clientInfo.getUDP_port());
			// 发送数据
			ds.send(dp);

		}
//		message.get
		// 创建数据并打包
//		byte[] bys = .getBytes();
//		DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("192.168.1.3"), 23333);	
//			//发送数据
//			ds.send(dp);
//		}
	}

}
