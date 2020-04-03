package TCP_single_thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {
	public static void main(String[] args) throws IOException {
		// ������������Socket����
		ServerSocket ss = new ServerSocket(23333);
		System.out.println("����: "+ss.getInetAddress().getHostAddress()+": "+ss.getLocalPort());
		
		// �����ͻ������� ����һ��Socket����
		Socket s = ss.accept();
		System.out.println("accept success: "+s.getInetAddress().getHostAddress()+" "+s.getLocalPort());
		// ��ȡ����������
		InputStream is = s.getInputStream();

		// ��ȡ����
		byte[] bys = new byte[1024];
		int len = is.read(bys);
		String str = new String(bys, 0, len);

		System.out.println(s.getInetAddress().getHostAddress() +" "+s.getLocalPort()+ "---" + str);
		
		//�ͷ���Դ
		s.close();

	}
}
