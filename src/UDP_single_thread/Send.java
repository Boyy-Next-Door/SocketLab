package UDP_single_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Send {
	public static void main(String[] args) throws IOException {
		// 创建发送端Socket对象
		DatagramSocket ds = new DatagramSocket();
		System.out.println("服务器："+"192.168.1.3:23333");
		System.out.println("input the text you want to send (input 'quit' to quit):");
		// 封装键盘录入数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = br.readLine()) != null) {
			//关闭发送端
			if(line.equals("quit")) {
				ds.close();
				break;
			}
			// 创建数据并打包
			byte[] bys = line.getBytes();
			DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("192.168.1.3"), 23333);
			
			//发送数据
			ds.send(dp);
		}
	}
}
