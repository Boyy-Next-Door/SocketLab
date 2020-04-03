package TCP_chat_room.model.db;

import java.util.Date;

/**
 * 	用户信息类实体
 * @author GeorgeYang
 */
public class UserInfo {
	private long id;				//主键id
	private long uid;			//用户id
	private String nickname;		//昵称
	private String portrait_url;		//头像url
	private int sex;				//性别
	private int age;				//年龄	
	private String school;			//学校
	private String major;			//专业
	private String class_str;		//班级
	private Date create_at;			//创建时间
	private Date update_at;			// 最后一次修改时间
	
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
