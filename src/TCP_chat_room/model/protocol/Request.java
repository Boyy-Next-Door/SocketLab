package TCP_chat_room.model.protocol;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 	�Զ����������
 * @author Shinelon
 *
 */
public class Request {
	private int requestType;		// 401-��½ 402-ע��  403-������Ϣ 404-�˳�
	private String srcIp;			//����Ip
	private int srcPort_tcp;			//���󷽶˿ں�
	private String destIp;			//���ܷ�ip
	private int destPort_tcp;			//���ܷ��˿ں�
	private String body;			//������
	private String cookie;			//cookie
	private Date requestAt;			//���󴴽�ʱ��
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	//����request��response��json��
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	//����json��������ΪRequest����
	public static Request parseRequestJson(String json) {
		return JSON.parseObject(json, Request.class);
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public String getSrcIp() {
		return srcIp;
	}

	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}

	public int getSrcPort_tcp() {
		return srcPort_tcp;
	}

	public void setSrcPort_tcp(int srcPort) {
		this.srcPort_tcp = srcPort;
	}

	public String getDestIp() {
		return destIp;
	}

	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	public int getDestPort_tcp() {
		return destPort_tcp;
	}

	public void setDestPort_tcp(int destPort) {
		this.destPort_tcp = destPort;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getRequestAt() {
		return requestAt;
	}

	public void setRequestAt(Date requestAt) {
		this.requestAt = requestAt;
	}
	
}
