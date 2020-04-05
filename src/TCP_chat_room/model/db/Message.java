package TCP_chat_room.model.db;

import java.util.Date;

/**
 *  消息类实体
 * @author Shinelon
 *
 */
public class Message {
	private long id;				//主键id
	private long sender_id;			//发送方id 系统拥有专属的一个用户id
	private long receiver_id;		//接受方id 
	private int message_type;		//301文本消息 302图片 				...
	private int channel_type;		//311私聊  312群聊 313系统通知		...
	private String content;			//消息内容 具体内容与消息类型对应
	private Date create_at;			//创建时间
	private Date update_at;			//最后一次修改时间
	
	//冗余数据 不用入库
	private String senderNickname;		//发送方昵称
	private String receiverNickname;	//接受方昵称 当群聊时这个可以用于回复某条信息
	
	public String getSenderNickname() {
		return senderNickname;
	}
	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}
	public String getReceiverNickname() {
		return receiverNickname;
	}
	public void setReceiverNickname(String receiverNickname) {
		this.receiverNickname = receiverNickname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSender_id() {
		return sender_id;
	}
	public void setSender_id(long sender_id) {
		this.sender_id = sender_id;
	}
	public long getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(long receiver_id) {
		this.receiver_id = receiver_id;
	}
	public int getMessage_type() {
		return message_type;
	}
	public void setMessage_type(int message_type) {
		this.message_type = message_type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	public Date getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}
	
	public int getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(int channel_type) {
		this.channel_type = channel_type;
	}
	//校验message内容
	public boolean checkMSG() {
		return content!=null&&content.length()>0;
	}
	

	
}

