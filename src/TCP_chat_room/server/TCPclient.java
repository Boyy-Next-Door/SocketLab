package TCP_chat_room.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import TCP_chat_room.GUI.ChatFrame;
import TCP_chat_room.GUI.GroupChatFrame;
import TCP_chat_room.GUI.UIOperator;
import TCP_chat_room.model.ClientInfo;
import TCP_chat_room.model.Const;
import TCP_chat_room.model.db.Message;
import TCP_chat_room.model.db.UserInfo;
import TCP_chat_room.model.protocol.Request;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.utils.MD5Util;

public class TCPclient {
	private ClientInfo clientInfo;
	private Socket tcpSocketSender = null;
	private DatagramSocket udpSocketReceiver = null;
	private GroupChatFrame groupChatframe = null;
	private UDPListenerThread udpListenerThread = null;

	public void setGroupChatframe(GroupChatFrame groupChatframe) {
		this.groupChatframe = groupChatframe;
		// 需要给线程中的引用也赋值
		udpListenerThread.setGroupChatFrame(groupChatframe);
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	// 构造TCPclient的时候注入代理操作接口的实现类
	public TCPclient(GroupChatFrame frame) {
		this.groupChatframe = frame;
	}

	public void main(String[] args) throws IOException, InterruptedException {
		// 初始化客户端
		initClient();
		// 登陆
		// System.out.println(login("u123","1234"));
		// 注册
		// System.out.println(register("666666", "hahaha"));

		// 释放资源 关闭socket连接
		// s.close();
		// Connection refused: connect TCP协议一定要先开启服务器
	}

	// 初始化客户端 创建与服务器端的TCP连接
	public void initClient() throws UnknownHostException, IOException {
		// 创建客户端Socket对象 这一步如果成功 说明创建连接成功
		tcpSocketSender = new Socket(Const.SERVER_IP, Const.SERVER_PORT);
		System.out.println(
				"本机: " + tcpSocketSender.getLocalAddress().getHostAddress() + ": " + tcpSocketSender.getLocalPort());
		System.out.println("服务器：" + tcpSocketSender.getRemoteSocketAddress());
		// 创建客户端用于接受服务器端发送消息的udpsocket 为保证本地多个客户端独立运行 这里的端口采用动态获取可用空闲端口号
		udpSocketReceiver = new DatagramSocket();
		// 为udpsocket创建监听线程 以响应服务器发来的消息
		this.udpListenerThread = new UDPListenerThread(udpSocketReceiver, groupChatframe);
		this.udpListenerThread.start();
		return;
	}

	// 获取response的内容
	private static String readData(InputStream inputStream) throws IOException, InterruptedException {
		// 封装数据源
		int count = 0;
		while (count == 0) {
			count = inputStream.available();
			Thread.sleep(100);
		}
		if (count != 0) {
			byte[] bt = new byte[count];
			int readCount = 0;
			while (readCount < count) {
				readCount += inputStream.read(bt, readCount, count - readCount);
			}
			String trim = new String(bt).trim();
			System.out.println(trim);
			return trim;
		}
		return "";
	}

	// 封装登陆接口
	public boolean login(String username, String password) throws IOException, InterruptedException {
		// 校验参数
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// 获取输出流
		OutputStream os = tcpSocketSender.getOutputStream();
		// 构造Request对象
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.LOGIN);
		// 注意 这里由于业务需求 需要把客户端已经创建好的udp端口号一并传入 在登陆成功后由服务器记录
		request.setBody("{\"username\":\"" + username + "\",\"password\":\"" + MD5Util.MD5Encode(password, "utf8")
				+ "\"" + ",\"udpPort\":" + udpSocketReceiver.getLocalPort() + "}");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// 向服务器写请求字符串
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// 等待服务器的response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		System.out.println("response: " + response);
		if (response.getRet() == 1) {
			System.out.println("登陆成功");
			System.out.println("response.getData(): " + response.getData());
			clientInfo = JSON.parseObject(response.getData().toString(), ClientInfo.class);
			System.out.println("clientInfo: " + clientInfo);
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// 封装注册接口
	public boolean register(String username, String password) throws IOException, InterruptedException {
		// 校验参数
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// 获取输出流
		OutputStream os = tcpSocketSender.getOutputStream();
		// 构造Request对象
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.REGISTER);
		request.setBody(
				"{\"username\":\"" + username + "\",\"password\":\"" + MD5Util.MD5Encode(password, "utf8") + "\"}");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// 向服务器写请求字符串
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// 等待服务器的response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("注册成功");
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// 封装发送消息接口
	public boolean sendMSG(Message message) throws IOException, InterruptedException {
		// 校验参数
		if (message == null) {
			return false;
		}
		// 获取输出流
		OutputStream os = tcpSocketSender.getOutputStream();
		// 构造Request对象
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.SENDMSG);
		request.setCookie(clientInfo.getCookie().getCookie());
		request.setBody(JSON.toJSONString(message)); // 将message按照json字符串的形式存入body
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// 向服务器写请求字符串
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// 等待服务器的response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("消息发送成功");
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// 封装查询在线人数接口
	public int queryOnlineNumber() throws IOException, InterruptedException {

		// 获取输出流
		OutputStream os = tcpSocketSender.getOutputStream();
		// 构造Request对象
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.ONLINE_NUMBER);
		request.setCookie(clientInfo.getCookie().getCookie());
		request.setBody("");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// 向服务器写请求字符串
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// 等待服务器的response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("查询在线人数成功");
			return (int) response.getData();
		} else {
			System.out.println(response.getMessage());
			return 0;
		}
	}

	// 退出函数
	public void exit() {
		try {
			// 获取输出流
			OutputStream os = tcpSocketSender.getOutputStream();
			// 构造Request对象
			Request request = new Request();
			request.setDestIp(Const.SERVER_IP);
			request.setDestPort_tcp(Const.SERVER_PORT);
			request.setRequestType(Const.RequestTypeEnum.LOGOUT);
			request.setCookie(clientInfo.getCookie().getCookie());
			request.setBody("");
			String nextLine = Request.toJson(request);
			// System.out.println(nextLine);
			// 向服务器写请求字符串
			PrintWriter pWriter = new PrintWriter(os, true);
			pWriter.println(nextLine);
			pWriter.flush();
			// 等待服务器的response

			Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
			// System.out.println(readData);
			if (response.getRet() == 1) {
				System.out.println("可以退出");
			} else {
				System.out.println("退出接口未成功");
			}
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 断开连接 释放资源
		try {
			tcpSocketSender.close();
			udpSocketReceiver.close();
			udpListenerThread.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class UDPListenerThread extends Thread {
	private DatagramSocket ds = null;
	private GroupChatFrame groupChatFrame = null;

	public UDPListenerThread(DatagramSocket udpSocketReceiver, GroupChatFrame frame) {
		this.ds = udpSocketReceiver;
		this.groupChatFrame = frame;
	}

	public void setGroupChatFrame(GroupChatFrame groupChatFrame) {
		this.groupChatFrame = groupChatFrame;
	}

	@Override
	public void run() {
		System.out.println("客户端udp监听线程开启！ port:" + ds.getLocalPort());
		try {
			// 自旋监听获取服务器发来的数据包
			while (true) {
				byte[] bys = new byte[1024];
				// 创建包
				DatagramPacket dp = new DatagramPacket(bys, bys.length);

				// 接受数据包 在返回之前会阻塞
				ds.receive(dp);
				System.out.println("received a packet from: " + dp.getAddress().getHostAddress() + ":" + dp.getPort());

				// 解析并打印
				byte[] bys2 = dp.getData();
				String s = new String(bys2, 0, bys2.length);
				System.out.println(dp.getAddress().getHostAddress() + " --- " + s);
				Message message = JSON.parseObject(s, Message.class);
				// 由于TCPclient中的groupChatFrame对象是在其被初始化结束之后 才会被注入
				// 所以可能出现 刚登陆上 还没加载完聊天页面 UDP就收到message的情况 这时候应该忽略这条消息
				if (groupChatFrame == null) {
					continue;
				}
				// 是群聊消息
				if (message.getChannel_type() == Const.MessageChannelTypeTypeEnum.GROUP_CHAT) {
					// 调用ui操作接口
					groupChatFrame.showMessage(message);
				}
				// 是更新在线人数
				else if (message
						.getChannel_type() == Const.MessageChannelTypeTypeEnum.SYSTEM_NOTIFICATION_ONLINE_NUMBER) {
					groupChatFrame.updateOnlineNumber(message.getContent());
				}
			}
		} catch (Exception e) {
			// 监听线程出现问题了
			System.out.println("客户端udp监听线程结束");
		}
	}
}