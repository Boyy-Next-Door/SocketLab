package TCP_chat_room.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;

import TCP_chat_room.model.Const;
import TCP_chat_room.model.protocol.Request;
import TCP_chat_room.model.protocol.Response;
import TCP_chat_room.utils.MD5Util;

public class TCPclient {
	private static String cookie = "";
	private static Socket socket = null;

	public static void main(String[] args) throws IOException, InterruptedException {
		// ��ʼ���ͻ���
		initClient();
		// ��½
		// System.out.println(login("u123","1234"));
		// ע��
		System.out.println(register("2123", "hahaha"));

		// �ͷ���Դ �ر�socket����
		// s.close();
		// Connection refused: connect TCPЭ��һ��Ҫ�ȿ���������
	}

	// ��ʼ���ͻ��� ������������˵�TCP����
	public static void initClient() throws UnknownHostException, IOException {
		// �����ͻ���Socket���� ��һ������ɹ� ˵���������ӳɹ�
		socket = new Socket(Const.SERVER_IP, Const.SERVER_PORT);
		System.out.println("����: " + socket.getLocalAddress().getHostAddress() + ": " + socket.getLocalPort());
		System.out.println("��������" + socket.getRemoteSocketAddress());
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
	public static boolean login(String username, String password) throws IOException, InterruptedException {
		// У�����
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// ��ȡ�����
		OutputStream os = socket.getOutputStream();
		// ����Request����
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.LOGIN);
		request.setBody(
				"{\"username\":\"" + username + "\",\"password\":\"" + MD5Util.MD5Encode(password, "utf8") + "\"}");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// �������д�����ַ���
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// �ȴ���������response
		Response response = JSON.parseObject(readData(socket.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("��½�ɹ�");
			cookie = response.getData().toString();
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// ��װע��ӿ�
	public static boolean register(String username, String password) throws IOException, InterruptedException {
		// У�����
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// ��ȡ�����
		OutputStream os = socket.getOutputStream();
		// ����Request����
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort(Const.SERVER_PORT);
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
		Response response = JSON.parseObject(readData(socket.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("ע��ɹ�");
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}
}
