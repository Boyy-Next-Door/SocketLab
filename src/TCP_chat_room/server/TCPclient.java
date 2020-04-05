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
		// ��Ҫ���߳��е�����Ҳ��ֵ
		udpListenerThread.setGroupChatFrame(groupChatframe);
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	// ����TCPclient��ʱ��ע���������ӿڵ�ʵ����
	public TCPclient(GroupChatFrame frame) {
		this.groupChatframe = frame;
	}

	public void main(String[] args) throws IOException, InterruptedException {
		// ��ʼ���ͻ���
		initClient();
		// ��½
		// System.out.println(login("u123","1234"));
		// ע��
		// System.out.println(register("666666", "hahaha"));

		// �ͷ���Դ �ر�socket����
		// s.close();
		// Connection refused: connect TCPЭ��һ��Ҫ�ȿ���������
	}

	// ��ʼ���ͻ��� ������������˵�TCP����
	public void initClient() throws UnknownHostException, IOException {
		// �����ͻ���Socket���� ��һ������ɹ� ˵���������ӳɹ�
		tcpSocketSender = new Socket(Const.SERVER_IP, Const.SERVER_PORT);
		System.out.println(
				"����: " + tcpSocketSender.getLocalAddress().getHostAddress() + ": " + tcpSocketSender.getLocalPort());
		System.out.println("��������" + tcpSocketSender.getRemoteSocketAddress());
		// �����ͻ������ڽ��ܷ������˷�����Ϣ��udpsocket Ϊ��֤���ض���ͻ��˶������� ����Ķ˿ڲ��ö�̬��ȡ���ÿ��ж˿ں�
		udpSocketReceiver = new DatagramSocket();
		// Ϊudpsocket���������߳� ����Ӧ��������������Ϣ
		this.udpListenerThread = new UDPListenerThread(udpSocketReceiver, groupChatframe);
		this.udpListenerThread.start();
		return;
	}

	// ��ȡresponse������
	private static String readData(InputStream inputStream) throws IOException, InterruptedException {
		// ��װ����Դ
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

	// ��װ��½�ӿ�
	public boolean login(String username, String password) throws IOException, InterruptedException {
		// У�����
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// ��ȡ�����
		OutputStream os = tcpSocketSender.getOutputStream();
		// ����Request����
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.LOGIN);
		// ע�� ��������ҵ������ ��Ҫ�ѿͻ����Ѿ������õ�udp�˿ں�һ������ �ڵ�½�ɹ����ɷ�������¼
		request.setBody("{\"username\":\"" + username + "\",\"password\":\"" + MD5Util.MD5Encode(password, "utf8")
				+ "\"" + ",\"udpPort\":" + udpSocketReceiver.getLocalPort() + "}");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// �������д�����ַ���
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// �ȴ���������response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		System.out.println("response: " + response);
		if (response.getRet() == 1) {
			System.out.println("��½�ɹ�");
			System.out.println("response.getData(): " + response.getData());
			clientInfo = JSON.parseObject(response.getData().toString(), ClientInfo.class);
			System.out.println("clientInfo: " + clientInfo);
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// ��װע��ӿ�
	public boolean register(String username, String password) throws IOException, InterruptedException {
		// У�����
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// ��ȡ�����
		OutputStream os = tcpSocketSender.getOutputStream();
		// ����Request����
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.REGISTER);
		request.setBody(
				"{\"username\":\"" + username + "\",\"password\":\"" + MD5Util.MD5Encode(password, "utf8") + "\"}");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// �������д�����ַ���
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// �ȴ���������response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("ע��ɹ�");
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// ��װ������Ϣ�ӿ�
	public boolean sendMSG(Message message) throws IOException, InterruptedException {
		// У�����
		if (message == null) {
			return false;
		}
		// ��ȡ�����
		OutputStream os = tcpSocketSender.getOutputStream();
		// ����Request����
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.SENDMSG);
		request.setCookie(clientInfo.getCookie().getCookie());
		request.setBody(JSON.toJSONString(message)); // ��message����json�ַ�������ʽ����body
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// �������д�����ַ���
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// �ȴ���������response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("��Ϣ���ͳɹ�");
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// ��װ��ѯ���������ӿ�
	public int queryOnlineNumber() throws IOException, InterruptedException {

		// ��ȡ�����
		OutputStream os = tcpSocketSender.getOutputStream();
		// ����Request����
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort_tcp(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.ONLINE_NUMBER);
		request.setCookie(clientInfo.getCookie().getCookie());
		request.setBody("");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// �������д�����ַ���
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// �ȴ���������response
		Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("��ѯ���������ɹ�");
			return (int) response.getData();
		} else {
			System.out.println(response.getMessage());
			return 0;
		}
	}

	// �˳�����
	public void exit() {
		try {
			// ��ȡ�����
			OutputStream os = tcpSocketSender.getOutputStream();
			// ����Request����
			Request request = new Request();
			request.setDestIp(Const.SERVER_IP);
			request.setDestPort_tcp(Const.SERVER_PORT);
			request.setRequestType(Const.RequestTypeEnum.LOGOUT);
			request.setCookie(clientInfo.getCookie().getCookie());
			request.setBody("");
			String nextLine = Request.toJson(request);
			// System.out.println(nextLine);
			// �������д�����ַ���
			PrintWriter pWriter = new PrintWriter(os, true);
			pWriter.println(nextLine);
			pWriter.flush();
			// �ȴ���������response

			Response response = JSON.parseObject(readData(tcpSocketSender.getInputStream()), Response.class);
			// System.out.println(readData);
			if (response.getRet() == 1) {
				System.out.println("�����˳�");
			} else {
				System.out.println("�˳��ӿ�δ�ɹ�");
			}
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// �Ͽ����� �ͷ���Դ
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
		System.out.println("�ͻ���udp�����߳̿����� port:" + ds.getLocalPort());
		try {
			// ����������ȡ���������������ݰ�
			while (true) {
				byte[] bys = new byte[1024];
				// ������
				DatagramPacket dp = new DatagramPacket(bys, bys.length);

				// �������ݰ� �ڷ���֮ǰ������
				ds.receive(dp);
				System.out.println("received a packet from: " + dp.getAddress().getHostAddress() + ":" + dp.getPort());

				// ��������ӡ
				byte[] bys2 = dp.getData();
				String s = new String(bys2, 0, bys2.length);
				System.out.println(dp.getAddress().getHostAddress() + " --- " + s);
				Message message = JSON.parseObject(s, Message.class);
				// ����TCPclient�е�groupChatFrame���������䱻��ʼ������֮�� �Żᱻע��
				// ���Կ��ܳ��� �յ�½�� ��û����������ҳ�� UDP���յ�message����� ��ʱ��Ӧ�ú���������Ϣ
				if (groupChatFrame == null) {
					continue;
				}
				// ��Ⱥ����Ϣ
				if (message.getChannel_type() == Const.MessageChannelTypeTypeEnum.GROUP_CHAT) {
					// ����ui�����ӿ�
					groupChatFrame.showMessage(message);
				}
				// �Ǹ�����������
				else if (message
						.getChannel_type() == Const.MessageChannelTypeTypeEnum.SYSTEM_NOTIFICATION_ONLINE_NUMBER) {
					groupChatFrame.updateOnlineNumber(message.getContent());
				}
			}
		} catch (Exception e) {
			// �����̳߳���������
			System.out.println("�ͻ���udp�����߳̽���");
		}
	}
}