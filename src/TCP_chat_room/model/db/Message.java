package TCP_chat_room.model.db;

import java.util.Date;

/**
 *  ��Ϣ��ʵ��
 * @author Shinelon
 *
 */
public class Message {
	private long id;				//����id
	private long sender_id;			//˽��Ϊ���ͷ�id ������Ϊ-1
	private long receiver_id;		//˽��Ϊ���ܷ�id ������Ϊ-1
	private int message_type;		//301�ı���Ϣ 302ͼƬ 303��������...
	private String content;			//��Ϣ���� ������������Ϣ���Ͷ�Ӧ
	private Date create_at;			//����ʱ��
	private Date update_at;			//���һ���޸�ʱ��
	
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
