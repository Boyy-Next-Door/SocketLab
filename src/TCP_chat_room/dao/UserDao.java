package TCP_chat_room.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import TCP_chat_room.model.Const;
import TCP_chat_room.model.db.User;
import TCP_chat_room.model.protocol.Cookie;
import TCP_chat_room.utils.C3P0Utils;

/*ÓÃ»§Ä£¿édao*/
public class UserDao {
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

	public boolean verifyUser(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(1) from tb_user where username=? and password=?";
		return ((Long) runner.query(sql, new ScalarHandler(), username, password)) > 0 ? true : false;
	}

	public boolean isUsernameExist(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(1) from tb_user where username=?";
		return ((Long) runner.query(sql, new ScalarHandler(), username)) > 0 ? true : false;
	}

	public void insertCookie(Cookie ck) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "INSERT INTO tb_user_cookie ( uid, cookie, create_at ) VALUES ( ?, ?, ? ) "
				+ "ON DUPLICATE KEY UPDATE cookie = ?,create_at = ?;";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String create_at = simpleDateFormat.format(ck.getCreate_at());
		runner.update(sql, ck.getUid(), ck.getCookie(), create_at, ck.getCookie(), create_at);
	}

	public boolean insertUser(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "INSERT INTO tb_user ( username,password,user_type) VALUES ( ?, ?, ?)";
		return ( runner.update(sql,user.getUsername(),user.getPassword(),Const.UserTypeEnum.NORMAL_USER)) > 0 ? true : false;
	}

}
