package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private String accdbPath;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public void init() throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		accdbPath = "data/Database2.accdb";
		conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT*FROM todo");
	}
	
	public void close() throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}
	
	public String get
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
