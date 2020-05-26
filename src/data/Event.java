package data;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Event {

	private String title, detials, priority, state;
	private Calendar deadline;
	private int eventID;
		
	public Event(int eventID, String title, String priority, String state, Calendar deadline, String details){
		this.setEventID(eventID);
		this.setTitle(title);
		this.setPriority(priority);
		this.setState(state);
		this.setDeadline(deadline);
		this.setDetials(details);
	}
	
	//写入
	public void setEventID(int neweventID) {
		eventID = neweventID;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setPriority(String newPriority) {
		priority = newPriority;
	}
	
	public void setState(String newState) {
		state = newState;
	}
	
	public void setDetials(String newDetials) {
		detials = newDetials;
	}
	//设置时间//注意calendar类的初始月份为0
	public void setDeadline(Calendar newDeadline) {
		deadline = newDeadline;
	}
	
	//读取
	public int getEventID() {
		return eventID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDetials() {
		return detials;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public String getState() {
		return state;
	}
	
	public Calendar getDeadline() {
		return deadline;
	}
	
	public void getEventInfo(Event e) {
		System.out.println(e.getEventID() + " "
				+ e.getTitle() + " "
				+ e.getPriority() + " "
				+ e.getState() + " "
				+ e.getStringTime(e.getDeadline()) + " "
				+ e.getDetials());
	}
	//时间
	public String getStringTime(Calendar c) {
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
		return sdf.format(d);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//event类测试
//		Event eve = new Event("hello", "today", "tomorrow", 1, 0);
//		System.out.println(eve.getTitle() + eve.getState());
		
		//日期生成、转换测试
		Calendar c = Calendar.getInstance();
		c.set(2020, 5 - 1, 25, 23, 8);//calendar类MONTH的初值为0
//		System.out.println(c.get(c.YEAR) + "-" + c.get(c.MONTH) + "-" + c.get(c.DAY_OF_MONTH) + " "
//				+ c.get(c.HOUR_OF_DAY) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
//		Date d = c.getTime();
//		Calendar c1 = Calendar.getInstance();
//		c1.setTime(d);
//		System.out.println(c1.get(c.YEAR) + "-" + c1.get(c.MONTH) + "-" + c1.get(c.DAY_OF_MONTH) + " "
//				+ c1.get(c.HOUR_OF_DAY) + ":" + c1.get(c.MINUTE) + ":" + c1.get(c.SECOND));
		Event e = new Event(0, null, null, null, c, null);
		System.out.println(e.getStringTime(c));
		
		//event + calendar test
//		Calendar ddl = Calendar.getInstance();
//		Calendar ddl1 = Calendar.getInstance();
//		ddl.set(2020, 5, 30, 8, 10);
//		ddl1.set(2020, 5, 26, 17, 18);
//		System.out.println(ddl.compareTo(ddl1));
//		Event e = new Event(1, "hello", 1, 1, ddl, null);
//		System.out.println(e.getStringTime(e.deadline));
	}

}
