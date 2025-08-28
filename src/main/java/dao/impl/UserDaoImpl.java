package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import dao.UserDao;
import model.User;
import util.DBConnection;

public class UserDaoImpl implements UserDao {

	@Override
	public User findByUserName(String username) {
		String sql = "SELECT * FROM Users WHERE username = ?";
		try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setUserName(rs.getString("username"));
					user.setFullName(rs.getString("fullname"));
					user.setPassWord(rs.getString("password"));
					user.setAvatar(rs.getString("avatar"));
					user.setRoleid(rs.getInt("roleid"));
					user.setPhone(rs.getString("phone"));
					Timestamp ts = rs.getTimestamp("createdDate");
					if (ts != null)
						user.setCreatedDate(new java.util.Date(ts.getTime()));
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User login(String username, String password) {
		String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
		try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setUserName(rs.getString("username"));
					user.setFullName(rs.getString("fullname"));
					user.setPassWord(rs.getString("password"));
					user.setAvatar(rs.getString("avatar"));
					user.setRoleid(rs.getInt("roleid"));
					user.setPhone(rs.getString("phone"));
					Timestamp ts = rs.getTimestamp("createdDate");
					if (ts != null)
						user.setCreatedDate(new java.util.Date(ts.getTime()));
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
