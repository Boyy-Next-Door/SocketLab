package TCP_chat_room.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import TCP_chat_room.model.Const;
import TCP_chat_room.model.db.Message;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.utils.C3P0Utils;

/*ÓÃ»§Ä£¿édao*/
public class MessageDao {
//	public Long checkUsername(String username) throws SQLException {
//		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
//		Long query = (Long)runner.query("select count(*) from user where username=?", new ScalarHandler(),username);
//		return query;
//	}
//	
//	public boolean addUser(User user) throws SQLException {
//		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
//		String sql = "insert into user values(?,?,?,?,?,?,?)";
//		int update = runner.update(sql, null,user.getUsername(),user.getPassword(),user.getEmail(),user.getUserGender(),user.getBirthday(),user.getRealname());
//		return update>0?true:false;
//	}

	public boolean insertGroupMessage(Message message) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "INSERT INTO tb_chat_message (sender_id,receiver_id,message_type,content,channel_type) "
				+ "SELECT ?,?,?,?,? WHERE "
				+ "(EXISTS(SELECT 1=1 FROM tb_user WHERE uid=?) AND EXISTS(SELECT 1=1 FROM tb_chat_group WHERE group_id=?))";
		return ( runner.update(sql,message.getSender_id(),message.getReceiver_id(),message.getMessage_type(),message.getContent(),
				message.getChannel_type(),message.getSender_id(),message.getReceiver_id())) > 0 ? true : false;
	}
	
	public boolean insertPrivateMessage(Message message) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "INSERT INTO tb_chat_message (sender_id,receiver_id,message_type,content,channel_type) "
				+ "SELECT ?,?,?,?,? WHERE "
				+ "((SELECT COUNT(1) FROM tb_user WHERE (uid=?) OR (uid=?))=2)";
		return ( runner.update(sql,message.getSender_id(),message.getReceiver_id(),message.getMessage_type(),message.getContent(),
				message.getChannel_type(),message.getSender_id(),message.getReceiver_id())) > 0 ? true : false;
	}

}
