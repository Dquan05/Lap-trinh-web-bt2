package util;

import java.sql.Connection;
import java.sql.Statement;

public class DBInit {
	public static void init() {
		String create = "CREATE TABLE IF NOT EXISTS Users (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "email VARCHAR(255), " + "username VARCHAR(100) UNIQUE, " + "fullname VARCHAR(255), "
				+ "password VARCHAR(255), " + "avatar VARCHAR(255), " + "roleid INT, " + "phone VARCHAR(50), "
				+ "createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ")";
		String upsertAdmin = "MERGE INTO Users (username, password, email, fullname, roleid) KEY(username) "
				+ "VALUES ('admin','123','admin@example.com','Quản trị viên',1)";

		try (Connection c = new DBConnection().getConnection(); Statement s = c.createStatement()) {
			s.execute(create);
			s.execute(upsertAdmin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
