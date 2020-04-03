package TCP_chat_room.model.protocol;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 	自定义的请求类
 * @author Shinelon
 *
 */
public class Request {
	private int requestType;		// 401-登陆 402-注册  403-发送消息 404-退出
	private String srcIp;			//请求方Ip
	private int srcPort;			//请求方端口号
	private String destIp;			//接受方ip
	private int destPort;			//接受方端口号
	private String body;			//请求体
	private Date requestAt;			//请求创建时间
	
	//用于request和response的json化
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	//解析json串的内容为Request对象
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
