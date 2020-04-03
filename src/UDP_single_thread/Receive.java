package UDP_single_thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * 	ʵ�ֶ�ν��� ���ر�
 */
public class Receive {
	public static void main(String[] args) throws IOException {
		// �������ܶ���
		DatagramSocket ds = new DatagramSocket(23333);
		while (true) {
			byte[] bys = new byte[1024];
			// ������
			DatagramPacket dp = new DatagramPacket(bys, bys.length);

			// ����
			ds.receive(dp);
			
			System.out.println("received a packet from: "+dp.getAddress().getHostAddress()+":"+dp.getPort());

			// ��������ӡ
			byte[] bys2 = dp.getData();
			String s = new String(bys2, 0, bys2.length);

			System.out.println(dp.getAddress().getHostAddress() 
					+ " --- " + s);
		}
	}
}
