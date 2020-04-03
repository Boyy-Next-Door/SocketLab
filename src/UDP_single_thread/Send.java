package UDP_single_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Send {
	public static void main(String[] args) throws IOException {
		// �������Ͷ�Socket����
		DatagramSocket ds = new DatagramSocket();
		System.out.println("��������"+"192.168.1.3:23333");
		System.out.println("input the text you want to send (input 'quit' to quit):");
		// ��װ����¼������
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = br.readLine()) != null) {
			//�رշ��Ͷ�
			if(line.equals("quit")) {
				ds.close();
				break;
			}
			// �������ݲ����
			byte[] bys = line.getBytes();
			DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("192.168.1.3"), 23333);
			
			//��������
			ds.send(dp);
		}
	}
}
