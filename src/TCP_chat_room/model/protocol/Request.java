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
	private int srcPort;			//���󷽶˿ں�
	private String destIp;			//���ܷ�ip
	private int destPort;			//���ܷ��˿ں�
	private String body;			//������
	private Date requestAt;			//���󴴽�ʱ��
	
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

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public String getDestIp() {
		return destIp;
	}

	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	public int getDestPort() {
		return destPort;
	}

	public void setDestPort(int destPort) {
		this.destPort = destPort;
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
