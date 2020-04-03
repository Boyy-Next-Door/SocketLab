package TCP_chat_room.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import TCP_chat_room.controller.UserController;
import TCP_chat_room.model.Const;
import TCP_chat_room.model.protocol.Request;
import TCP_chat_room.model.protocol.Response;

public class TCPserver {
	private static ExecutorService cachedThreadPool;

	public static void main(String[] args) throws IOException {
		// 初始化线程池
		initThreadPool();
		// 创建服务器端Socket对象
		ServerSocket ss = initSocket(23333);
		// 监听客户端连接 返回一个Socket对象
		while (true) {
			// 接收到一个TCP连接请求 丢给服务端业务线程处理
			Socket accept = ss.accept();
			System.out.println(accept.getInetAddress().getHostAddress() + " " + accept.getPort() + "创建tcp连接");
			// 给线程池分发一个新任务
			cachedThreadPool.execute(new ExecuteThread(accept));
		}
	}

	// 初始化ServerSocket对象
	private static ServerSocket initSocket(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out
				.println("本机: " + serverSocket.getInetAddress().getHostAddress() + ": " + serverSocket.getLocalPort());
		return serverSocket;
	}

	// 初始化线程池
	private static void initThreadPool() {
		cachedThreadPool = Executors.newCachedThreadPool();
	}
}

/* 线程池中的具体任务线程类 */
class ExecuteThread implements Runnable {
	private Socket socket;
	private String clientInfo;
	private String clientIp;
	private int clientPort;

	// 构造传入socket实例
	public ExecuteThread(Socket s) {
		this.socket = s;
		this.clientInfo = socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort();
		this.clientIp = socket.getInetAddress().getHostAddress();
		this.clientPort = socket.getLocalPort();

	}

	private String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return simpleDateFormat.format(date);
	}

	// 业务逻辑
	private void execute() throws IOException, InterruptedException {
		// 解析请求
		Request request = Request.parseRequestJson(readData());
		// ip和端口号在客户端打包封装请求的时候获取不到 需要服务器端建立tcp连接后才能确定
		request.setSrcIp(clientIp);
		request.setSrcPort(clientPort);
		if (request != null) {
			// 根据request的具体类型 向controller分发任务
			switch (request.getRequestType()) {
			case Const.RequestTypeEnum.LOGIN: {
				Response login = UserController.login(request);
				// 将response写回给客户端
				writeBack(login);
				break;
			}
			case Const.RequestTypeEnum.LOGOUT: {

			}
			case Const.RequestTypeEnum.SENDMSG: {

			}
			case Const.RequestTypeEnum.REGISTER: {
				Response login = UserController.register(request);
				// 将response写回给客户端
				writeBack(login);
				break;
			}
			default: {
				break;
			}
			}
		}
	}

	private String readData() throws IOException, InterruptedException {
		// 封装数据源
		InputStream inputStream = socket.getInputStream();
		int count = 0;
		while (count == 0) {
			count = inputStream.available();
		}
		if (count != 0) {
			System.out.println(count);
			byte[] bt = new byte[count];
			int readCount = 0;
			while (readCount < count) {
				readCount += inputStream.read(bt, readCount, count - readCount);
			}
			return new String(bt);
		}
		return "";
	}

	// 向客户端回写响应
	private void writeBack(Response login) throws IOException {
		String json = Request.toJson(login);
		OutputStream outputStream = socket.getOutputStream();
		System.out.println("response to " + clientInfo + ": " + json);
		outputStream.write(json.getBytes());
	}

	@Override
	public void run() {
		try {
			execute();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
