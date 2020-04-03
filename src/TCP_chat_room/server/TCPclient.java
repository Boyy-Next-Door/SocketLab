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
		// 初始化客户端
		initClient();
		// 登陆
		// System.out.println(login("u123","1234"));
		// 注册
		System.out.println(register("2123", "hahaha"));

		// 释放资源 关闭socket连接
		// s.close();
		// Connection refused: connect TCP协议一定要先开启服务器
	}

	// 初始化客户端 创建与服务器端的TCP连接
	public static void initClient() throws UnknownHostException, IOException {
		// 创建客户端Socket对象 这一步如果成功 说明创建连接成功
		socket = new Socket(Const.SERVER_IP, Const.SERVER_PORT);
		System.out.println("本机: " + socket.getLocalAddress().getHostAddress() + ": " + socket.getLocalPort());
		System.out.println("服务器：" + socket.getRemoteSocketAddress());
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
	public static boolean login(String username, String password) throws IOException, InterruptedException {
		// 校验参数
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// 获取输出流
		OutputStream os = socket.getOutputStream();
		// 构造Request对象
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort(Const.SERVER_PORT);
		request.setRequestType(Const.RequestTypeEnum.LOGIN);
		request.setBody(
				"{\"username\":\"" + username + "\",\"password\":\"" + MD5Util.MD5Encode(password, "utf8") + "\"}");
		String nextLine = Request.toJson(request);
		// System.out.println(nextLine);
		// 向服务器写请求字符串
		PrintWriter pWriter = new PrintWriter(os, true);
		pWriter.println(nextLine);
		pWriter.flush();
		// 等待服务器的response
		Response response = JSON.parseObject(readData(socket.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("登陆成功");
			cookie = response.getData().toString();
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}

	// 封装注册接口
	public static boolean register(String username, String password) throws IOException, InterruptedException {
		// 校验参数
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			return false;
		}
		// 获取输出流
		OutputStream os = socket.getOutputStream();
		// 构造Request对象
		Request request = new Request();
		request.setDestIp(Const.SERVER_IP);
		request.setDestPort(Const.SERVER_PORT);
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
		Response response = JSON.parseObject(readData(socket.getInputStream()), Response.class);
		// System.out.println(readData);
		if (response.getRet() == 1) {
			System.out.println("注册成功");
			return true;
		} else {
			System.out.println(response.getMessage());
			return false;
		}
	}
}
