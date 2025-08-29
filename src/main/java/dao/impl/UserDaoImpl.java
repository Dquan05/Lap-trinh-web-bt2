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
					return mapResultSetToUser(rs);
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
					return mapResultSetToUser(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO Users (email, username, fullname, password, avatar, roleId, phone, createdDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getPassWord());
			ps.setString(5, user.getAvatar());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getPhone());
			ps.setTimestamp(8, new java.sql.Timestamp(user.getCreatedDate().getTime()));

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findByPhone(String phone) {
		String sql = "SELECT * FROM Users WHERE phone = ?";
		try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, phone);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT * FROM Users WHERE email = ?";
		try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private User mapResultSetToUser(ResultSet rs) throws Exception {
		Timestamp ts = rs.getTimestamp("createdDate");
		return new User(rs.getInt("id"), rs.getString("email"), rs.getString("username"), rs.getString("fullname"),
				rs.getString("password"), rs.getString("avatar"), rs.getInt("roleId"), rs.getString("phone"),
				ts != null ? new java.util.Date(ts.getTime()) : null);
	}
}
