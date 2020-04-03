package TCP_single_thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {
	public static void main(String[] args) throws IOException {
		// 创建服务器端Socket对象
		ServerSocket ss = new ServerSocket(23333);
		System.out.println("本机: "+ss.getInetAddress().getHostAddress()+": "+ss.getLocalPort());
		
		// 监听客户端连接 返回一个Socket对象
		Socket s = ss.accept();
		System.out.println("accept success: "+s.getInetAddress().getHostAddress()+" "+s.getLocalPort());
		// 获取输入流对象
		InputStream is = s.getInputStream();

		// 读取数据
		byte[] bys = new byte[1024];
		int len = is.read(bys);
		String str = new String(bys, 0, len);

		System.out.println(s.getInetAddress().getHostAddress() +" "+s.getLocalPort()+ "---" + str);
		
		//释放资源
		s.close();

	}
}
