package TCP_single_thread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPclient {
	public static void main(String[] args) throws IOException{
		//�����ͻ���Socket����  ��һ������ɹ� ˵���������ӳɹ�
		Socket s = new Socket("192.168.1.3", 23333);
		System.out.println("����: "+s.getLocalAddress().getHostAddress()+": "+s.getLocalPort());
		System.out.println("��������"+s.getRemoteSocketAddress());
		
		System.out.println("input the text you want to send:");
		Scanner scanner = new Scanner(System.in);
		String nextLine = scanner.nextLine();
		
		//��ȡ�����
		OutputStream os = s.getOutputStream();
		
		//�������
		os.write(nextLine.getBytes());
		
		//�ͷ���Դ
		os.close();
		s.close();
		//Connection refused: connect  TCPЭ��һ��Ҫ�ȿ���������
	}
}
