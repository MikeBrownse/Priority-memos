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
	//���ݿ��ʼ��
	public void init() throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		accdbPath = "data/Database2.accdb";
		conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT*FROM todo");		
	}
	//�ر����ݿ����
	public void close() throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}
	//�����ȡ,��ͷ����null
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
	//�洢
	public void save(Events e) throws SQLException, ClassNotFoundException {
		stmt.executeUpdate("insert into todo(����,���ȼ�,״̬,��ֹʱ��,�¼�����) values('"+e.getTitle()+"','"+e.getPriority()+"','"+e.getState()+"','"+e.getDeadline().getTimeInMillis()+"','"+e.getDetials()+"')");
		e.setEventID(getLastID());
	}
	//����
	public void change(int eID) throws SQLException {
//		stmt.executeUpdate("update todo set ����='"+e.getTitle()+"',���ȼ�='"+e.getPriority()+"',״̬='"+e.getState()+"',��ֹʱ��='"+e.getDeadline().getTimeInMillis()+"',�¼�����='"+e.getDetials()+"' where ID='"+e.getEventID()+"' ");
		String fin = "���";
		stmt.executeUpdate("update todo set ״̬='"+fin+"' where ID='"+eID+"' ");
	}
	//����Ϊ˽�з���
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
		return rs.getString("����");
	}
	
	private String getPriority() throws SQLException {
		return rs.getString("���ȼ�");
	}
	
	private String getState() throws SQLException {
		return rs.getString("״̬");
	}
	
	private long getDeadline() throws SQLException {
		return rs.getLong("��ֹʱ��");
	}
	
	private String getDetails() throws SQLException {
		return rs.getString("�¼�����");
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
