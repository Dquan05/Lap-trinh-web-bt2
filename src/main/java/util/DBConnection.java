package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private final String serverName = "LAPTOP-HCL43H54";
	private final String dbName = "LoginDB";
	private final String portNumber = "1433"; // mặc định của SQL Server
	private final String instance = ""; // để trống nếu dùng mặc định
	private final String userID = "login_user";
	private final String password = "123456";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName
				+ ";encrypt=true;trustServerCertificate=true;";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
}
