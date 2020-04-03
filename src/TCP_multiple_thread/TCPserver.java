package TCP_multiple_thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			System.out.println(accept.getInetAddress().getHostAddress() + " " + accept.getPort() + "进入聊天室");
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

	// 构造传入socket实例
	public ExecuteThread(Socket s) {
		this.socket = s;
		this.clientInfo = socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort();

	}

	private String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return simpleDateFormat.format(date);
	}

	// 业务逻辑
	private void execute() {
		// 获取输入流对象
		InputStream is;
		// 读取数据
		byte[] bys = new byte[1024];
		int len=0;
		try {
			is = socket.getInputStream();
			len = is.read(bys);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = new String(bys, 0, len);

		System.out.println("--------------------------------------------------\n" + clientInfo + " " + getTimeStamp()
				+ "\n\t" + str + "\n--------------------------------------------------");

		// 释放资源
	}

	@Override
	public void run() {
		execute();
	}

}
