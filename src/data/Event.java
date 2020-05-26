package data;

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
	
	public String getEventString(Event e) {
		return null;
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
//		return String.format("%04d年%02d月%02日 %02d:%02d", c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH),
//				c.get(c.HOUR_OF_DAY), c.get(c.MINUTE));
		return (c.get(c.YEAR) + "年" + c.get(c.MONTH) + "月" + c.get(c.DAY_OF_MONTH) + "日 "
				+ c.get(c.HOUR_OF_DAY) + ":" + c.get(c.MINUTE));
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//event类测试
//		Event eve = new Event("hello", "today", "tomorrow", 1, 0);
//		System.out.println(eve.getTitle() + eve.getState());
		
		//日期生成、转换测试
//		Calendar c = Calendar.getInstance();
//		c.set(2020, 5, 25, 23, 8);
//		System.out.println(c.get(c.YEAR) + "-" + c.get(c.MONTH) + "-" + c.get(c.DAY_OF_MONTH) + " "
//				+ c.get(c.HOUR_OF_DAY) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
//		Date d = c.getTime();
//		Calendar c1 = Calendar.getInstance();
//		c1.setTime(d);
//		System.out.println(c1.get(c.YEAR) + "-" + c1.get(c.MONTH) + "-" + c1.get(c.DAY_OF_MONTH) + " "
//				+ c1.get(c.HOUR_OF_DAY) + ":" + c1.get(c.MINUTE) + ":" + c1.get(c.SECOND));
		
		//event + calendar test
		Calendar ddl = Calendar.getInstance();
		Calendar ddl1 = Calendar.getInstance();
		ddl.set(2020, 5, 30, 8, 10);
		ddl1.set(2020, 5, 26, 17, 18);
		System.out.println(ddl.compareTo(ddl1));
//		Event e = new Event(1, "hello", 1, 1, ddl, null);
//		System.out.println(e.getStringTime(e.deadline));
	}

}
