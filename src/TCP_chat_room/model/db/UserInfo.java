package TCP_chat_room.model.db;

import java.util.Date;

/**
 * 	�û���Ϣ��ʵ��
 * @author GeorgeYang
 */
public class UserInfo {
	private long id;				//����id
	private long uid;			//�û�id
	private String nickname;		//�ǳ�
	private String portrait_url;		//ͷ��url
	private int sex;				//�Ա�
	private int age;				//����	
	private String school;			//ѧУ
	private String major;			//רҵ
	private String class_str;		//�༶
	private Date create_at;			//����ʱ��
	private Date update_at;			// ���һ���޸�ʱ��
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getNick_name() {
		return nickname;
	}
	public void setNick_name(String nick_name) {
		this.nickname = nick_name;
	}
	public String getPortrait_url() {
		return portrait_url;
	}
	public void setPortrait_url(String portrait_url) {
		this.portrait_url = portrait_url;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClass_str() {
		return class_str;
	}
	public void setClass_str(String class_str) {
		this.class_str = class_str;
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
