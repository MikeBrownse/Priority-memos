package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Database {

	private String accdbPath;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	//数据库初始化
	public void init() throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		accdbPath = "data/Database2.accdb";
		conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT*FROM todo");		
	}
	//关闭数据库调用
	public void close() throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}
	//单项读取,到头返回null
	public Events read() throws NumberFormatException, SQLException, ClassNotFoundException {
		Events e = null;
		getNextID();
		if(!rs.isAfterLast()) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(getDeadline());
			e = new Events(getCurrentID(), getTitle(), getPriority(), getState(), c, getDetails());
		}
		return e;
	}
	//存储
	public void save(Events e) throws SQLException, ClassNotFoundException {
		stmt.executeUpdate("insert into todo(标题,优先级,状态,截止时间,事件详情) values('"+e.getTitle()+"','"+e.getPriority()+"','"+e.getState()+"','"+e.getDeadline().getTimeInMillis()+"','"+e.getDetials()+"')");
		e.setEventID(getLastID());
	}
	//更改
	public void change(int eID) throws SQLException {
//		stmt.executeUpdate("update todo set 标题='"+e.getTitle()+"',优先级='"+e.getPriority()+"',状态='"+e.getState()+"',截止时间='"+e.getDeadline().getTimeInMillis()+"',事件详情='"+e.getDetials()+"' where ID='"+e.getEventID()+"' ");
		String fin = "完成";
		stmt.executeUpdate("update todo set 状态='"+fin+"' where ID='"+eID+"' ");
	}
	//以下为私有方法
	private int getNextID() throws SQLException {
		int ret = 0;
		rs.next();
		if(!rs.isAfterLast()) {
			ret = rs.getInt("ID");
		}
		return ret;
	}
	
	private int getLastID() throws SQLException, ClassNotFoundException {
		int newid = 0;
		close();
		init();
		while(rs.next()) {
			if(rs.isLast()) {
				newid = rs.getInt("ID");
			}
		}
		return newid;
	}
	
	private int getCurrentID() throws SQLException {
		return rs.getInt("ID");
	}
	
	private String getTitle() throws SQLException {
		return rs.getString("标题");
	}
	
	private String getPriority() throws SQLException {
		return rs.getString("优先级");
	}
	
	private String getState() throws SQLException {
		return rs.getString("状态");
	}
	
	private long getDeadline() throws SQLException {
		return rs.getLong("截止时间");
	}
	
	private String getDetails() throws SQLException {
		return rs.getString("事件详情");
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Database db = new Database();
		db.init();
		Events e = db.read();
		e.getEventInfo();
		e = db.read();
		e.getEventInfo();
		db.change(e.getEventID());
		db.close();
		
		Database db1 = new Database();
		db1.init();
		Events e1 = db1.read();
		e1.getEventInfo();
		e1 = db1.read();
		e1.getEventInfo();
		db1.close();
	}
}
