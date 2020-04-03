package TCP_chat_room.model.db;

import java.util.Date;
/**
 * 	用户类实体
 * @author GeorgeYang
 */
public class User {
	private long uid;		//用户id
	private String username;	//用户名
	private String password;	//MD5加密密码 
	private int user_type;		//普通用户 101  管理员 102
	private Date create_at;		//创建时间
	private Date update_at;		//最后一次修改时间
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
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
