package TCP_chat_room.model.db;

import java.util.Date;

/**
 *  消息类实体
 * @author Shinelon
 *
 */
public class Message {
	private long id;				//主键id
	private long sender_id;			//私聊为发送方id 聊天室为-1
	private long receiver_id;		//私聊为接受方id 聊天室为-1
	private int message_type;		//301文本消息 302图片 303更多类型...
	private String content;			//消息内容 具体内容与消息类型对应
	private Date create_at;			//创建时间
	private Date update_at;			//最后一次修改时间
	
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
	

	
}

