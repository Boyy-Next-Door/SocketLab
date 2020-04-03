package UDP_single_thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * 	实现多次接受 不关闭
 */
public class Receive {
	public static void main(String[] args) throws IOException {
		// 创建接受对象
		DatagramSocket ds = new DatagramSocket(23333);
		while (true) {
			byte[] bys = new byte[1024];
			// 创建包
			DatagramPacket dp = new DatagramPacket(bys, bys.length);

			// 接受
			ds.receive(dp);
			
			System.out.println("received a packet from: "+dp.getAddress().getHostAddress()+":"+dp.getPort());

			// 解析并打印
			byte[] bys2 = dp.getData();
			String s = new String(bys2, 0, bys2.length);

			System.out.println(dp.getAddress().getHostAddress() 
					+ " --- " + s);
		}
	}
}
