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
		// ��ʼ���̳߳�
		initThreadPool();
		// ������������Socket����
		ServerSocket ss = initSocket(23333);

		// �����ͻ������� ����һ��Socket����
		while (true) {
			// ���յ�һ��TCP�������� ���������ҵ���̴߳���
			Socket accept = ss.accept();
			System.out.println(accept.getInetAddress().getHostAddress() + " " + accept.getPort() + "����������");
			// ���̳߳طַ�һ��������
			cachedThreadPool.execute(new ExecuteThread(accept));
		}
	}

	// ��ʼ��ServerSocket����
	private static ServerSocket initSocket(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out
				.println("����: " + serverSocket.getInetAddress().getHostAddress() + ": " + serverSocket.getLocalPort());
		return serverSocket;
	}

	// ��ʼ���̳߳�
	private static void initThreadPool() {
		cachedThreadPool = Executors.newCachedThreadPool();
	}
}

/* �̳߳��еľ��������߳��� */
class ExecuteThread implements Runnable {
	private Socket socket;
	private String clientInfo;

	// ���촫��socketʵ��
	public ExecuteThread(Socket s) {
		this.socket = s;
		this.clientInfo = socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort();

	}

	private String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return simpleDateFormat.format(date);
	}

	// ҵ���߼�
	private void execute() {
		// ��ȡ����������
		InputStream is;
		// ��ȡ����
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

		// �ͷ���Դ
	}

	@Override
	public void run() {
		execute();
	}

}
