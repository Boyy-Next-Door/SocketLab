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
import java.rmi.server.Skeleton;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import TCP_chat_room.controller.MessageController;
import TCP_chat_room.controller.UserController;
import TCP_chat_room.model.Const;
import TCP_chat_room.model.protocol.Request;
import TCP_chat_room.model.protocol.Response;

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
			System.out.println(accept.getInetAddress().getHostAddress() + " " + accept.getPort() + "����tcp����");
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
	private String clientIp;
	private int clientPort;

	// ���촫��socketʵ��
	public ExecuteThread(Socket s) {
		this.socket = s;
		this.clientInfo = socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort();
		this.clientIp = socket.getInetAddress().getHostAddress();
		this.clientPort = socket.getPort();

	}

	private String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return simpleDateFormat.format(date);
	}

	// ҵ���߼�
	private void execute() throws IOException, InterruptedException {
		//������������
		while(!socket.isClosed()) {
			// ��������
			String readData = readData();
			if("soket is closed".equals(readData)) {
				//˵��readDataʱ�Ѿ�����socket�Ͽ���
				break;
			}
			Request request = Request.parseRequestJson(readData);
			// ip�Ͷ˿ں��ڿͻ��˴����װ�����ʱ���ȡ���� ��Ҫ�������˽���tcp���Ӻ����ȷ��
			request.setSrcIp(clientIp);
			request.setSrcPort_tcp(clientPort);
			if (request != null) {
				// ����request�ľ������� ��controller�ַ�����
				switch (request.getRequestType()) {
				case Const.RequestTypeEnum.LOGIN: {
					Response login = UserController.login(request);
					// ��responseд�ظ��ͻ���
					writeBack(login);
					break;
				}
				case Const.RequestTypeEnum.LOGOUT: {
					Response logout = UserController.logout(request);
					// ��responseд�ظ��ͻ���
					writeBack(logout);
					break;
				}
				case Const.RequestTypeEnum.SENDMSG: {
					Response sendMSG = MessageController.sendMSG(request);
					// ��responseд�ظ��ͻ���
					writeBack(sendMSG);
					break;
				}
				case Const.RequestTypeEnum.REGISTER: {
					Response register = UserController.register(request);
					// ��responseд�ظ��ͻ���
					writeBack(register);
					break;
				}
				case Const.RequestTypeEnum.ONLINE_NUMBER: {
					int onlineUserNum = ServerManager.getSM().getOnlineUserNum();
					// ��responseд�ظ��ͻ���
					writeBack(Response.createBySuccess("��ѯ���������ɹ�", onlineUserNum));
					break;
				}
				default: {
					break;
				}
				}
			}	
		}	
		
		//socket�Ѿ��ر� �û��ر��˿ͻ���  ��û�е�½���û�Ҳ����socket���� �����޷������Ƿ�Ϊ�ѵ�¼�û�
		//����socket�ϵ�֮������֮ǰ��ѭ��һֱ���� û�취��ȷ������  ����Ժ������ 
		//�Ͳ����˳��ӿڵķ����� ��ǿ�ƹرջ��õÿ��������˵�socket����״̬��⣩
//		ServerManager.getSM().userLogout(clientInfo);
		
	}

	private String readData() throws IOException, InterruptedException {
		// ��װ����Դ
		InputStream inputStream = socket.getInputStream();
		int count = 0;
		while (count == 0) {
			Thread.sleep(200);
			//���socket�Ƿ�Ͽ�
			if(socket.isClosed()) {
				return "soket is closed";
			}
			count = inputStream.available();
		}
		if (count != 0) {
			byte[] bt = new byte[count];
			int readCount = 0;
			while (readCount < count) {
				readCount += inputStream.read(bt, readCount, count - readCount);
			}
			return new String(bt);
		}
		return "";
	}

	// ��ͻ��˻�д��Ӧ
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
